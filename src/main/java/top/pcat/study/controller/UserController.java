package top.pcat.study.controller;

import com.google.gson.Gson;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.domain.LoginReq;
import top.pcat.study.domain.UserInfo;
import top.pcat.study.entity.User;
import top.pcat.study.service.UserInfoService;
import top.pcat.study.service.UserService;
import top.pcat.study.shiro.JWTUtil;
import top.pcat.study.utils.Msg;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping( "/users")
public class UserController {

    private static final Gson gson = new Gson();

    @Autowired
    private UserService userService;

    @Resource
    private MinioClient minioClient;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 通过 手机号+密码 登录
     */
    @GetMapping("/{phone}/{password}")
    public Msg login(@PathVariable String phone, @PathVariable String password) {

        //获取该用户密码
        User user = userService.getAllByPhone(phone);
        if (user.getPassword() == null) {
            return Msg.fail().mes("用户名错误");
        }
        Md5Hash md5Hash = new Md5Hash(password, user.getSalt(), 1024);
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
    @PutMapping("/{userId}/infos/{bucket}")
    public Msg upImg(@PathVariable String userId,@PathVariable String bucket, HttpServletRequest request) {
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
    @GetMapping("/{userId}/infos/{bucket}")
    public Msg getImg(@PathVariable String userId,@PathVariable String bucket) {
        // Create a minioClient with the MinIO Server name, Port, Access key and Secret key.
        // Check if the bucket already exists.
        InputStream result=null;
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if(!isExist){
                return Msg.fail().mes("bucket不存在");
            }
        } catch (Exception e) {
            return Msg.fail().mes("获取失败").data(e.toString());
        }
        String url=null;
        try {
            url=minioClient.getPresignedObjectUrl(new GetPresignedObjectUrlArgs().builder()
                    .bucket(bucket)
                    .object(userId + ".png")
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            return Msg.fail().mes("获取失败").data(e.toString());
        }
        return Msg.success().mes("获取成功").data(url);
    }
}
