package top.pcat.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserDAO;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;
import top.pcat.study.service.UserService;
import top.pcat.study.utils.SaltUtils;

import java.util.List;

@Transactional
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(String username, String password){
        return userDAO.login(username, password);
    }

    @Override
    public String getIdByPhone(String phone){
        return userDAO.getIdByPhone(phone);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userDAO.findPermsByRoleId(id);
    }

    @Override
    public User findRolesById(String id) {
        return userDAO.findRolesById(id);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public String getPassword(String phone) {
        return userDAO.getPassword(phone);
    }

    @Override
    public String getRole(String username) {
        return null;
    }

    @Override
    public void updatePassword(String username, String newPassword) {

    }

    @Override
    public List<String> getUser() {
        return null;
    }

    @Override
    public void banUser(String username) {

    }

    @Override
    public int checkUserBanStatus(String username) {
        return 0;
    }

    @Override
    public String getRolePermission(String username) {
        return null;
    }

    @Override
    public String getPermission(String username) {
        return null;
    }

    @Override
    public void register(User user) {
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDAO.save(user);
    }
}
