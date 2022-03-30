package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ClassDao;
import top.pcat.study.dao.RongDao;
import top.pcat.study.pojo.Clasp;

import java.util.List;

@Transactional
@Service
@Slf4j
public class ClassService {

    @Autowired
    ClassDao classDao;

    @Autowired
    RongDao rongDao;


    public int createClass(String adminId,String className) {
        try{
            classDao.createClass(adminId, className);
            int id= classDao.selectIdByName(className);
            rongDao.createGroup(adminId,String.valueOf(id),className);
            classDao.joinClass(adminId, String.valueOf(id));
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public List<Clasp> getClassList(String userId) {
        return classDao.getClassList(userId);
    }

    public int joinClass(String userId, String classId) {
        return classDao.joinClass(userId,classId);
    }

    public List<Clasp> getCreateClassList(String userId) {

        return classDao.getCreateClassList(userId);
    }
}
