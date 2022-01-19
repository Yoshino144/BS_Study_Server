package top.pcat.study.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ChapterMapper;
import top.pcat.study.dao.SubjectMapper;
import top.pcat.study.dao.UserChooseMapper;
import top.pcat.study.domain.Chapter;
import top.pcat.study.domain.Subject;
import top.pcat.study.domain.Yixuan;
import top.pcat.study.service.SubjectService;
import top.pcat.study.utils.StringNullAdapter;

import java.util.List;
@Transactional
@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    UserChooseMapper userChooseMapper;


    @Autowired
    ChapterMapper chapterMapper;

//    public String upYixuan(Integer id,String all){
//        UserChoose userChoose = new UserChoose();
////        userChoose.setSize();
////        this.userChooseMapper.insert()
//    }

    @Override
    public String getSubject() {
        List<Subject> subjectList =  this.subjectMapper.selectByExample();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, new StringNullAdapter());
        Gson gson = gsonBuilder.create();
        return gson.toJson(subjectList);
    }

    @Override
    public String getYixuan(String userId) {
        List<Yixuan> subjectList =  this.userChooseMapper.selectByExample(userId);
        System.out.println(userId);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, new StringNullAdapter());
        Gson gson = gsonBuilder.create();
        return gson.toJson(subjectList);
    }

//    @Override
//    public String getSize(String userId) {
//
//        Integer yixSize = this.userChooseMapper.yixSize(userId);
//        System.out.println("已选题数："+yixSize);
//        List<Integer> yixSub = this.userChooseMapper.yixSubject(userId);
//        int all = 0;
//        for(Integer yi:yixSub){
//            Integer allSize = this.userChooseMapper.allSize(yi);
//            if(allSize!=null){
//                all += allSize;
//            }
//        }
//
//        return String.valueOf(all)+"&"+String.valueOf(yixSize);
//
//    }



}
