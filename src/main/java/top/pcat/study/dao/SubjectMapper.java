package top.pcat.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.Subject;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select("select * from subject")
    List<Subject> selectByExample();

}