package top.pcat.study.service;



import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;

import java.util.List;

@Service
public interface UserService {

    String getPassword(String username);

    /**
     * 获得角色权限
     */
    String getRole(String username);

    /**
     * 修改密码
     */
    void updatePassword(String username,String newPassword);

    /**
     * 获得存在的用户
     */
    List<String> getUser();

    /**
     * 封号
     */
    void banUser(String username);

    /**
     * 检查用户状态
     */
    int checkUserBanStatus(String username);

    /**
     * 获得用户角色默认的权限
     */
    String getRolePermission(String username);

    /**
     * 获得用户的权限
     */
    String getPermission(String username);
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
