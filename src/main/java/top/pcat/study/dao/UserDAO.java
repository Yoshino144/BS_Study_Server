package top.pcat.study.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;

import java.util.List;

@Mapper
public interface UserDAO {

    @Insert("insert into t_user values(#{id},#{username},#{password},#{salt})")
    void save(User user);

    @Select("select id,username,password,salt from t_user where username = #{username}")
    User findByUserName(String username);

    //根据用户名查询所有角色
    @Select("SELECT u.id uid,u.username,r.id,r.NAME rname\n" +
            "      FROM t_user u\n" +
            "      LEFT JOIN t_user_role ur\n" +
            "      ON u.id=ur.userid\n" +
            "      LEFT JOIN t_role r\n" +
            "      ON ur.roleid=r.id\n" +
            "      WHERE u.username=#{username}")
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    @Select("SELECT p.id,p.NAME,p.url,r.NAME\n" +
            "      FROM t_role r\n" +
            "      LEFT JOIN t_role_perms rp\n" +
            "      ON r.id=rp.roleid\n" +
            "      LEFT JOIN t_perms p ON rp.permsid=p.id\n" +
            "      WHERE r.id=#{id}")
    List<Perms> findPermsByRoleId(String id);
}

