package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.domain.Subject;
import top.pcat.study.domain.Yixuan;
import top.pcat.study.service.SubjectService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subjects")
public class SubjectController {


    @Autowired
    SubjectService subjectService;

    @GetMapping("/{userId}/un_official")
    public List<Subject> getUnOfficialSubject(@PathVariable String userId) {
        return this.subjectService.getSubject(0,userId);
    }

    @GetMapping("/{userId}/official")
    public List<Subject> getSubject(@PathVariable String userId) {
        return this.subjectService.getSubject(1,userId);
    }


    @GetMapping("/{userId}")
    public List<Subject> getYixuan(@PathVariable String userId) {
        return this.subjectService.getYixuan(userId);
    }

    @PostMapping("/{subjectId}/users/{userId}")
    public int upUserChoose(@PathVariable String subjectId,@PathVariable String userId){

        return this.subjectService.upUserChoose(subjectId,userId);
    }

    @DeleteMapping("/{subjectId}/users/{userId}")
    public int delUserChoose(@PathVariable String subjectId,@PathVariable String userId){

        return this.subjectService.delUserChoose(subjectId,userId);
    }
}
