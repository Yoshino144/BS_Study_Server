package top.pcat.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.Chapter;

import java.util.List;
@Mapper
public interface ChapterDao {

    @Select("select * from chapter where subject_id =#{subjectId} ")
    List<Chapter> getChapterById(String subjectId);

}