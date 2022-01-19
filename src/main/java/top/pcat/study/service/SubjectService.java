package top.pcat.study.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.pcat.study.dao.ChapterMapper;
import top.pcat.study.dao.SubjectMapper;
import top.pcat.study.dao.UserChooseMapper;
import top.pcat.study.domain.Chapter;
import top.pcat.study.domain.Subject;
import top.pcat.study.domain.Yixuan;

import java.util.List;

@Service
public interface  SubjectService {
    public String getSubject();

    public String getYixuan(String userId);

//    public String getSize(String userId);


}
