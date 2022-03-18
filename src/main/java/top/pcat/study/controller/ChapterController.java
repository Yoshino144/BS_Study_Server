package top.pcat.study.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pcat.study.pojo.Chapter;
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

    @GetMapping("/page/{subjectId}")
    public String pageGetChapterById(@PathVariable String subjectId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return "{\n" +
                "\t\"data\": "+gson.toJson(this.chapterService.getChapterById(subjectId))+",\n" +
                "\t\"total\": 100,\n" +
                "\t\"success\": true,\n" +
                "\t\"pageSize\": 20,\n" +
                "\t\"current\": 1\n" +
                "}";
    }
}
