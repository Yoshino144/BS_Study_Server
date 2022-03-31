package top.pcat.study.controller;

import com.google.gson.Gson;
import io.minio.*;
import io.minio.http.Method;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.LoginReq;
import top.pcat.study.pojo.UserInfo;
import top.pcat.study.entity.User;
import top.pcat.study.service.AccoutService;
import top.pcat.study.service.UserInfoService;
import top.pcat.study.service.UserService;
import top.pcat.study.shiro.JWTUtil;
import top.pcat.study.utils.Msg;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(tags = "用户管理")
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private static final Gson gson = new Gson();

    @Autowired
    private UserService userService;

    @Resource
    private MinioClient minioClient;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AccoutService accoutService;

    @GetMapping("/rongToken")
    public String getRongToken(String userId){
        return accoutService.getRongToken(userId);
    }

    /**
     * 获取全部用户
     */
    @ApiOperation("获取全部用户")
    @GetMapping("/all")
    public String allUsers() {
        String s = userService.getAllUsers();
            return "{\"data\":" + s + ",\n" +
                    "\t\"total\": 100,\n" +
                    "\t\"success\": true,\n" +
                    "\t\"pageSize\": 20,\n" +
                    "\t\"current\": 1\n" +
                    "}";
    }

    /**
     * 通过 手机号+密码 登录
     */
    @ApiOperation("通过 手机号+密码 登录")
    @GetMapping("/{phone}/{password}")
    public Msg login(@PathVariable String phone, @PathVariable String password) {

        //获取该用户密码
        User user = userService.getAllByPhone(phone);
        if (user.getPassword() == null) {
            return Msg.fail().mes("用户名错误");
        }
        Md5Hash md5Hash = new Md5Hash(password, user.getSalt(), 1024);
        System.out.println(user.getPassword()+"--!!!--"+md5Hash);
        if (!user.getPassword().equals(md5Hash.toString())) {
            return Msg.fail().mes("密码错误");
        } else {
            String uuid = userService.getIdByPhone(phone);
            return Msg.success()
                    .mes("登录成功")
                    .data(new LoginReq(uuid, JWTUtil.createToken(uuid)));
        }

    }

    /**
     * 注册
     */
    @ApiOperation("注册")
    @PostMapping("/{phone}/{password}/{name}")
    public Msg register(@PathVariable String phone, @PathVariable String password, @RequestBody String name) {

        //获取是否存在该用户
        String realPassword = userService.getIdByPhone(phone);

        // 不存在该用户
        if (realPassword == null) {
            if (2 == userService.register(phone, password, name)) {

                return Msg.success().mes("注册成功");
            } else {
                return Msg.fail().mes("注册失败");
            }
        } else {
            return Msg.fail().mes("用户已存在");
        }
    }

    /**
     * 通过 手机号+验证码 登录
     */
    @ApiOperation("通过 手机号+验证码 登录")
    @GetMapping("/{phone}")
    public Msg loginOnlyPhone(@PathVariable String phone) {

        //获取该用户密码
        User user = userService.getAllByPhone(phone);
        if (user.getPassword() == null) {
            return Msg.fail().mes("用户名不存在");
        } else {

            String uuid = userService.getIdByPhone(phone);
            return Msg.success()
                    .mes("登录成功")
                    .data(new LoginReq(uuid, JWTUtil.createToken(uuid)));
        }

    }

    /**
     * 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/{userId}/infos")
    public Msg getUserInfo(@PathVariable String userId) {
        try {
            return Msg.success()
                    .mes("获取成功")
                    .data(userInfoService.getUserInfo(userId));
        } catch (Exception e) {
            return Msg.fail().mes("获取失败").data(e.toString());
        }

    }

    /**
     * 上传用户信息
     */
    @ApiOperation("上传用户信息")
    @PutMapping("/infos")
    public Msg putUserInfo(@RequestBody String userInfoJson) {
        try {
            return Msg.success()
                    .mes("修改成功")
                    .data(userInfoService.update(gson.fromJson(userInfoJson, UserInfo.class)));
        } catch (Exception e) {
            return Msg.fail().mes("修改失败").data(e.toString());
        }

    }

    /**
     * 上传用户头像
     */
    @ApiOperation("上传用户头像")
    @PutMapping("/{userId}/infos/{bucket}")
    public Msg upImg(@PathVariable String userId, @PathVariable String bucket, HttpServletRequest request) {
        // 头像 profile.photo
        String base64 = request.getParameter("base64");
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] result = decoder.decode(base64);
            InputStream inputStream = new ByteArrayInputStream(result);
            log.info("保存图片：" + userId + ".png");
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(userId + ".png")
                            .stream(inputStream, -1, 10485760)
                            .contentType("image/png")
                            .build());

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(userId + ".png")
                            .expiry(7, TimeUnit.DAYS)
                            .build());
            log.info(url);
            return Msg.success().mes("上传成功").data(url);
        } catch (Exception e) {
            log.warn(String.valueOf(e));
            return Msg.fail().mes("上传失败").data(e.toString());
        }
    }

    /**
     * 下载用户头像
     */
    @ApiOperation("下载用户头像")
    @Deprecated
    @GetMapping("/{userId}/infos/{bucket}/deprecated")
    public Msg getImgURrl(@PathVariable String userId, @PathVariable String bucket) {
        // Create a minioClient with the MinIO Server name, Port, Access key and Secret key.
        // Check if the bucket already exists.
        InputStream result = null;
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!isExist) {
                return Msg.fail().mes("bucket不存在");
            }
        } catch (Exception e) {
            return Msg.fail().mes("获取失败").data(e.toString());
        }
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(new GetPresignedObjectUrlArgs().builder()
                    .bucket(bucket)
                    .object(userId + ".png")
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            return Msg.fail().mes("获取失败").data(e.toString());
        }
        return Msg.success().mes("获取成功").data(url);
    }

    @ApiOperation("下载用户头像")
    @GetMapping("/{userId}/infos/{bucket}")
    // 头像 profile.photo
    public ResponseEntity<byte[]> getImgFile(HttpServletResponse response , @PathVariable String userId, @PathVariable String bucket) {
        try {
            InputStream in = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(userId + ".png")
                            .build());
            byte[] body = null;
            body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
            in.read(body);// 读入到输入流里面

            OutputStream out = response.getOutputStream();

            byte[] buff =new byte[1024];
            int index=0;
            //4、执行 写出操作
            while((index= in.read(buff))!= -1){
                out.write(buff, 0, index);
                out.flush();
            }
            out.close();

//            HttpHeaders headers = new HttpHeaders();// 设置响应头
//            headers.add("Content-Disposition", "attachment;filename=" +userId+".png");
//            HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
//            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
//            return response;
        }catch (Exception e){

        }

        return null;
    }
}
