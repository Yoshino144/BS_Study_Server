package top.pcat.study.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {

    @Insert("insert into t_account(user_id, open_code, category)\n" +
            "VALUES(#{uuid},#{rongToken},#{rong}) ")
    int insert(@Param("uuid") String uuid,@Param("rongToken")  String rongToken,@Param("rong")  String rong);
}
