package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pcat.study.domain.Chapter;
import top.pcat.study.service.ChapterService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chapters")
public class ChapterController {


    @Autowired
    ChapterService chapterService;

    @GetMapping("/{subjectId}")
    public List<Chapter> getChapterById(@PathVariable String subjectId) {
        return this.chapterService.getChapterById(subjectId);
    }
}
