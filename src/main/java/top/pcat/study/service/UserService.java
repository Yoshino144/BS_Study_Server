package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserDao;
import top.pcat.study.dao.UserInfoDao;
import top.pcat.study.pojo.UserInfo;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;
import top.pcat.study.utils.SaltUtils;
import top.pcat.study.utils.UUIDUtils;

import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Slf4j
@Service("userService")
public class UserService  {

    @Autowired
    private UserDao userDAO;
    @Autowired
    private UserInfoDao userInfoDao;

    public User getAllByPhone(String phone){
        return userDAO.getAllByPhone(phone);
    }

    public String getIdByPhone(String phone){
        return userDAO.getIdByPhone(phone);
    }

    public int register(String phone, String password, String name) {
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        String uuid= UUIDUtils.getUUID();
        int flag= 0;
        //插入账号
        flag += userDAO.insert(new User(uuid,md5Hash.toString(),salt,null));
        //插入用户数据
        flag += userInfoDao.insert(new UserInfo(uuid,phone,name));
        return flag;
    }


    public User login(String username, String password){
        return userDAO.login(username, password);
    }


    public List<Perms> findPermsByRoleId(String id) {
        return userDAO.findPermsByRoleId(id);
    }

    public User findRolesById(String id) {
        return userDAO.findRolesById(id);
    }

    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    public String getPassword(String phone) {
        return userDAO.getPassword(phone);
    }

    public String getRole(String username) {
        return null;
    }

    public void updatePassword(String username, String newPassword) {

    }

    public List<String> getUser() {
        return null;
    }

    public void banUser(String username) {

    }

    public int checkUserBanStatus(String username) {
        return 0;
    }

    public String getRolePermission(String username) {
        return null;
    }

    public String getPermission(String username) {
        return null;
    }

}
