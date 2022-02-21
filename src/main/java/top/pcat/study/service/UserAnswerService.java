package top.pcat.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserAnswerDao;
import top.pcat.study.pojo.UserAnswerData;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 21:23
 **/
@Transactional
@Service
public class UserAnswerService {
    @Autowired
    UserAnswerDao userAnswerDao;

    public List<UserAnswerData> getWrongProblem(String userId, Integer trueFlag){
        return userAnswerDao.getWrongProblem(userId, trueFlag);
    }

    public List<UserAnswerData> getAnswerProblem(String userId){
        return userAnswerDao.getAnswerProblem(userId);
    }


}
