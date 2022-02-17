package top.pcat.study.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ChapterDao;
import top.pcat.study.dao.SubjectDao;
import top.pcat.study.dao.UserChooseDao;
import top.pcat.study.domain.Subject;
import top.pcat.study.domain.Yixuan;
import top.pcat.study.utils.StringNullAdapter;

import java.util.List;
@Transactional
@Slf4j
@Service
public class SubjectService  {


    @Autowired
    SubjectDao subjectMapper;

    @Autowired
    UserChooseDao userChooseDao;


    @Autowired
    ChapterDao chapterDao;

//    public String upYixuan(Integer id,String all){
//        UserChoose userChoose = new UserChoose();
////        userChoose.setSize();
////        this.userChooseMapper.insert()
//    }

    public List<Subject> getSubject(int official,String userId) {
        return this.subjectMapper.selectByExample(official,userId);
    }

    public List<Subject> getYixuan(String userId) {
        return this.userChooseDao.selectByExample(userId);
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
