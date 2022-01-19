package top.pcat.study.service;


import top.pcat.study.domain.User;

public interface UserService {
    User signInByPsw(String phone,String password);
    User signInByPhone(String phone);

    int register(String name, String password, String phone);

}
