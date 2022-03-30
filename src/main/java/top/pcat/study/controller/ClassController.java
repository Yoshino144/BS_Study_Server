package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.Clasp;
import top.pcat.study.service.ClassService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    ClassService classService;

    /**
     * 创建班级
     */

    @PostMapping("/{userId}")
    public int createClass(@PathVariable String userId, @Param("className") String className){
        return classService.createClass(userId,className);
    }

    /**
     * 获取加入的班级列表
     */

    @GetMapping("/{userId}")
    public List<Clasp> getClassList(@PathVariable String userId){
        return classService.getClassList(userId);
    }

    /**
     * 获取创建的班级列表
     */

    @GetMapping("/admin/{userId}")
    public List<Clasp> getCreateClassList(@PathVariable String userId){
        return classService.getCreateClassList(userId);
    }

    /**
     * 加入班级
     */

    @PutMapping("/{userId}/{classId}")
    public int joinClass(@PathVariable String userId,@PathVariable String classId){
        return classService.joinClass(userId, classId);
    }
}
