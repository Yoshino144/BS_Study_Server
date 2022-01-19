package top.pcat.study.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.Yixuan;

import java.util.List;
@Mapper
public interface UserChooseMapper {

//    @Select("select sum(chapter_size) from chapter where id_subject = #{yi}")
//    Integer allSize(Integer yi);
//
//    @Select("SELECT sum(size) FROM choose_subject where id_user = #{userId}")
//    Integer yixSize(String userId);
//
//    @Select("SELECT subject_id FROM choose_subject where id_user = #{userId}")
//    List<Integer> yixSubject(String userId);

    @Select("SELECT\n" +
            "\tchoose_subject.id_subject,\n" +
            "\tname_subject,\n" +
            "\tchoose_time,\n" +
            "\tfounder_subject,\n" +
            "\ttime_subject,\n" +
            "\tprivate_subject,\n" +
            "\tsize_subject,\n" +
            "\tofficial_subject \n" +
            "FROM\n" +
            "\tchoose_subject,subject \n" +
            "WHERE\n" +
            "\tchoose_subject.id_subject = subject.id_subject \n" +
            "\tAND choose_subject.id_user = 'df6f4977e1711b31ff6481403cd6de2b'")
    List<Yixuan> selectByExample(String userId);
}