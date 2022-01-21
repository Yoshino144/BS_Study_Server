package top.pcat.study.service;


import org.springframework.stereotype.Service;
import top.pcat.study.domain.User;

@Service
public interface UserService {
    User signInByPsw(String phone,String password);
    User signInByPhone(String phone);

    int register(String name, String password, String phone);

}
