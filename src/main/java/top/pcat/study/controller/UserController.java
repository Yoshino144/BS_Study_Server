package top.pcat.study.controller;

import com.google.gson.Gson;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.service.UserService;
import top.pcat.study.utils.R;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private MinioClient minioClient;

    //登录
    @GetMapping("/{codeFlag}/{phone}/{password}")
    public String signIn(@PathVariable String codeFlag, @PathVariable String phone, @PathVariable String password) {

        log.warn(codeFlag+" "+phone+"  "+password);
        if (codeFlag.equals("0")) {
            Gson gson = new Gson();
            return gson.toJson(new R(200, userService.signInByPsw(phone, password)));
        } else {
            Gson gson = new Gson();
            return gson.toJson(new R(200, userService.signInByPhone(phone)));
        }
    }

    //注册
    @PostMapping("/{name}/{password}/{phone}")
    public String register(@PathVariable String name, @PathVariable String password, @PathVariable String phone) {
        Gson gson = new Gson();
        return gson.toJson(new R(200, userService.register(name, password, phone) > 0));
    }

    @PostMapping("/{userId}")
    public Boolean upImg(@PathVariable String userId, HttpServletRequest request) {
        String base64 = request.getParameter("base64");
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] result = decoder.decode(base64);
            InputStream inputStream = new ByteArrayInputStream(result);
            log.info("保存图片："+userId+".png");
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("profile.photo").object(userId + ".png").stream(
                                    inputStream, -1, 10485760)
                            .contentType("image/png")
                            .build());
            return true;
        } catch (Exception e) {
            log.warn(String.valueOf(e));
        }
        return false;
    }
//dsfksmdkfs
}
