package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pcat.study.entity.User;
import top.pcat.study.service.UserService;
import top.pcat.study.utils.R;
import top.pcat.study.utils.VerifyCodeUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public R login(String username, String password) {

        log.info("用户名: [{}]",username);
        log.info("密码: [{}]",password);
        try{
            User user = new User();
            Map<String,String> payload =  new HashMap<>();
            payload.put("id",user.getId());
            payload.put("name",user.getUsername());
            //生成JWT的令牌
            String token = JWTUtils.getToken(payload);
            return new R(200,"登录成功!",token);
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            return new R(403,"用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return new R(403,"密码错误!");
        }catch (Exception e){
            return new R(403,e.getMessage());
        }

    }


}
