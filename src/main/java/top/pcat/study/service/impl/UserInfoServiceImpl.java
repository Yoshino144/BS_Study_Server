package top.pcat.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserInfoDao;
import top.pcat.study.domain.UserInfo;
import top.pcat.study.service.UserInfoService;

@Transactional
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userDao;


    @Override
    public UserInfo signInByPsw(String phone, String password){

        log.warn(" "+phone+"  "+password);
        log.warn(userDao.signInByPsw(phone,password).toString());
        return userDao.signInByPsw(phone,password);
    }

    @Override
    public UserInfo signInByPhone(String phone){
        return userDao.signInByPhone(phone);
    }

    @Override
    public int register(String name, String password, String phone){
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        return userDao.insert(user);
    }


}
