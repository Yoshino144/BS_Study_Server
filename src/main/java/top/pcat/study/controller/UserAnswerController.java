package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.UserAnswerData;
import top.pcat.study.service.UserAnswerService;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 20:56
 **/

@Slf4j
@RestController
@RequestMapping("/userAnswers")
public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;

    /**
     * 获取错题列表
     * @param userId
     * @param trueFlag
     * @return
     */

    @GetMapping("/{userId}/{trueFlag}")
    public List<UserAnswerData> getWrongProblem(@PathVariable String userId, @PathVariable Integer trueFlag) {
        return this.userAnswerService.getWrongProblem(userId,trueFlag);
    }

    @GetMapping("/{userId}")
    public List<UserAnswerData> getWrongProblem(@PathVariable String userId) {
        return this.userAnswerService.getAnswerProblem(userId);
    }

    @PostMapping("/{userId}") //REPLACE INTO t_user_problem_data VALUES (3,'sf',4,5,6,'N',1)
    public List<UserAnswerData> upWrongProblem(@PathVariable String userId) {
        return this.userAnswerService.getAnswerProblem(userId);
    }

}
