package top.pcat.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.UserDateSize;
import top.pcat.study.domain.WrongProblem;

import java.util.List;

@Mapper
public interface UserDateDao {

    @Select("SELECT SUM(chapter_size) as yiXuanAllSize from choose_subject as cs , chapter as cp where cs.id_subject = cp.subject_id and cs.id_user = #{userId}")
    String getYiXuanAllSize(String userId);

    @Select("SELECT COUNT(*) as yiXuanDoSize from user_problem_data where user_id = #{userId}")
    String getYiXuanDoSize(String userId);

    @Select("select size from user_date_size where date=  curdate() and user_id = #{userId}")
    String getTodaySizeSingle(String userId);

    @Select("SELECT\n" +
            "\tup.answer,\n" +
            "\tup.true_flag,\n" +
            "\tsu.name_subject,\n" +
            "\tch.chapter_name,\n" +
            "\tsu.id_subject,\n" +
            "\tch.chapter_id \n" +
            "FROM\n" +
            "\tuser_problem_data AS up,\n" +
            "\tSUBJECT AS su,\n" +
            "\tchapter AS ch \n" +
            "WHERE\n" +
            "\tup.user_id = #{userId} \n" +
            "\tAND up.chapter_id = ch.chapter_id \n" +
            "\tAND up.subject_id = su.id_subject \n" +
            "\tAND up.true_flag = #{trueFlag}")
    List<WrongProblem> getWrongProblem(String userId, String trueFlag);


    @Select("select * from user_date_size where user_id = #{userId} and date = #{date}")
    List<UserDateSize> getTodaySize(String userId, String date);

    @Select("select date , size from user_date_size where user_id = #{userId} ORDER BY date DESC")
    List<UserDateSize> getWeekSize(String userId);



}