package top.pcat.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.UserDateSize;
import top.pcat.study.domain.WrongProblem;

import java.util.List;

@Mapper
public interface UserDateDao {

    @Select("SELECT SUM(chapter_size) as yiXuanAllSize from t_subject_choose as cs , t_chapter as cp where cs.subject_id = cp.subject_id and cs.user_id = #{userId}")
    String getYiXuanAllSize(String userId);

    @Select("SELECT COUNT(*) as yiXuanDoSize from t_user_problem_data where user_id = #{userId}")
    String getYiXuanDoSize(String userId);

    @Select("select size from t_user_daily_data where date=  curdate() and user_id = #{userId}")
    String getTodaySizeSingle(String userId);

    @Select("SELECT\n" +
            "\tup.answer,\n" +
            "\tup.true_flag,\n" +
            "\tsu.name_subject,\n" +
            "\tch.chapter_name,\n" +
            "\tsu.subject_id,\n" +
            "\tch.chapter_id \n" +
            "FROM\n" +
            "\tt_user_problem_data AS up,\n" +
            "\tt_SUBJECT AS su,\n" +
            "\tt_chapter AS ch \n" +
            "WHERE\n" +
            "\tup.user_id = #{userId} \n" +
            "\tAND up.chapter_id = ch.chapter_id \n" +
            "\tAND up.subject_id = su.subject_id \n" +
            "\tAND up.true_flag = #{trueFlag}")
    List<WrongProblem> getWrongProblem(@Param("userId")String userId, @Param("trueFlag")String trueFlag);


    @Select("select * from t_user_daily_data where user_id = #{userId} and date = #{date}")
    List<UserDateSize> getTodaySize(@Param("userId")String userId,@Param("date")String date);

    @Select("select date , size from t_user_daily_data where user_id = #{userId} ORDER BY date DESC")
    List<UserDateSize> getWeekSize(String userId);


//jjj
}