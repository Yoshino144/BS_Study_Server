package top.pcat.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.UserInfo;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

    @Select("select * from user where password = #{password} and phone = #{phone}")
    UserInfo signInByPsw(@Param("phone")String phone, @Param("password") String password);

    @Select("select * from user where phone = #{phone} ")
    UserInfo signInByPhone(String phone);

}
