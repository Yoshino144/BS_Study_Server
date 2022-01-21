package top.pcat.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserDao;
import top.pcat.study.domain.User;
import top.pcat.study.service.UserService;

@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User signInByPsw(String phone,String password){

        log.warn(" "+phone+"  "+password);
        log.warn(userDao.signInByPsw(phone,password).toString());
        return userDao.signInByPsw(phone,password);
    }

    @Override
    public User signInByPhone(String phone){
        return userDao.signInByPhone(phone);
    }

    @Override
    public int register(String name, String password, String phone){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        return userDao.insert(user);
    }


}
