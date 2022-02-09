package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pcat.study.entity.User;
import top.pcat.study.service.UserService;
import top.pcat.study.shiro.JWTUtil;
import top.pcat.study.utils.Msg;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 跳转到register请求
     */
    @RequestMapping("registerview")
    public String register(){
        System.out.println("跳转之register的html");
        return "register";
    }

    /**
     * 跳转到login请求
     */
    @RequestMapping("loginview")
    public String login(){
        System.out.println("跳转之login的html");
        return "login";
    }


    /**
     * 验证码方法
     */
    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code",code);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220,60,os,code);
    }

    /**
     * 用户注册
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/user/loginview";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/user/registerview";
        }
    }


    /**
     * 退出登录
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//退出用户
        return "redirect:/user/loginview";
    }


    @RequestMapping("/{codeFlag}/{phone}/{password}")
    public Msg login(@PathVariable String codeFlag, @PathVariable String phone,@PathVariable String password) {

        if ("0".equals(codeFlag)) {

            String realPassword = userService.getPassword(phone);
            if (realPassword == null) {
                return Msg.fail().mes("用户名错误");
            } else if (!realPassword.equals(password)) {
                return Msg.fail().mes("密码错误");
            } else {
                return Msg.success()
                        .mes("登录成功")
                        .data(JWTUtil.createToken(userService.getIdByPhone(phone)));
            }

        } else {
            //Gson gson = new Gson();
            //return gson.toJson(new Msg(200, userService.signInByPhone(phone)));
        }

        return null;
    }


}
