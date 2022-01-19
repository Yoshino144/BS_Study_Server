package top.pcat.study.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.domain.Problem;
import top.pcat.study.service.ProblemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @GetMapping("/{subjectId}/{chapterId}")
    public List<Problem> getProblem(@PathVariable String subjectId, @PathVariable  String chapterId) {
        return this.problemService.getProblem(subjectId,chapterId);
    }


}

