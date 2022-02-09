package top.pcat.study.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.Problem;

import java.util.List;

@Mapper
public interface ProblemDao {

    @Select("select * from problem where subject_id =#{subjectId} and chapter_id = #{chapterId}")
    List<Problem> selectByExample(@Param("subjectId")String subjectId, @Param("chapterId")String chapterId);

}