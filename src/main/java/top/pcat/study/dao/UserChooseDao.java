package top.pcat.study.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.Subject;
import top.pcat.study.domain.Yixuan;

import java.util.List;
@Mapper
public interface UserChooseDao {

//    @Select("select sum(chapter_size) from chapter where id_subject = #{yi}")
//    Integer allSize(Integer yi);
//
//    @Select("SELECT sum(size) FROM choose_subject where id_user = #{userId}")
//    Integer yixSize(String userId);
//
//    @Select("SELECT subject_id FROM choose_subject where id_user = #{userId}")
//    List<Integer> yixSubject(String userId);

    @Select("SELECT\n" +
            "\tt_subject.* \n" +
            "FROM\n" +
            "\tt_subject_choose\n" +
            "\tINNER JOIN t_subject ON t_subject_choose.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_subject_choose.user_id = #{userId}")
    List<Subject> selectByExample(String userId);
}