package top.pcat.study.service;


import org.springframework.stereotype.Service;
import top.pcat.study.domain.UserInfo;

@Service
public interface UserInfoService {
    UserInfo signInByPsw(String phone, String password);
    UserInfo signInByPhone(String phone);

    int register(String name, String password, String phone);

}
