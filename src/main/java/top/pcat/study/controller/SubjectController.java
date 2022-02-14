package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.service.SubjectService;

@Slf4j
@RestController
@RequestMapping("/subjects")
public class SubjectController {


    @Autowired
    SubjectService subjectService;


    @GetMapping
    public String getSubject() {
        return this.subjectService.getSubject();
    }


    @GetMapping("/{userId}")
    public String getYixuan(@PathVariable String userId) {
        System.out.println("con=="+userId);
        return this.subjectService.getYixuan(userId);
    }

}
