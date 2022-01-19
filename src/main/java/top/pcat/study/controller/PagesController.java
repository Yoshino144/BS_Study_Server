package top.pcat.study.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @GetMapping("/yixuan/{userId}")
    public String yixuan(String userId){
        return "yixuan";
    }
}
