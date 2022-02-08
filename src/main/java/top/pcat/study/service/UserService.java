package top.pcat.study.service;



import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;

import java.util.List;

@Service
public interface UserService {
    //注册用户方法
    void register(User user);


    @Select("select * " +
            "from t_user us,t_user_info ui " +
            "where us.password = #{password} " +
            "and ui.phone = #{phone} " +
            "and ui.id = us.id")
    User login(String username, String password);

    //根据用户名查询业务的方法
    User findByUserName(String username);

    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
