package top.pcat.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where phone = #{phone} and password = #{password}")
    User signInByPsw(String phone,String password);

    @Select("select * from user where phone = #{phone} ")
    User signInByPhone(String phone);

}
