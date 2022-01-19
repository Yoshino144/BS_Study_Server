package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.domain.WrongProblem;
import top.pcat.study.service.UserDateService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/userdates")
public class UserDateController {

    @Autowired
    UserDateService userDayService;

    @GetMapping("/4/{userId}")
    public String getSize(@PathVariable String userId) {
        return this.userDayService.getSize(userId);
    }

    @GetMapping("/3/{userId}/{trueFlag}")
    public List<WrongProblem> getWrongProblem(@PathVariable String userId, @PathVariable String trueFlag) {
        return this.userDayService.getWrongProblem(userId,trueFlag);
    }


    @GetMapping("/0/{userId}")
    public String getTodaySize(@PathVariable String userId) {
        try {
            Integer res = Math.toIntExact(this.userDayService.getTodaySize(userId));
            System.out.print("res===");
            System.out.print(res);
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }


    @GetMapping("/1/{userId}")
    public String getWeekSize(@PathVariable String userId) {
        try {
            String res = this.userDayService.getWeekSize(userId);
            return res;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
