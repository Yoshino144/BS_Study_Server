package top.pcat.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.domain.UserAnswerData;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 21:23
 **/

@Mapper
public interface UserAnswerDao {

    @Select("SELECT\n" +
            "\tt_user_problem_data.*,\n" +
            "\tt_chapter.chapter_name,\n" +
            "\tt_subject.subject_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.true_flag =#{trueFlag} \n" +
            "\tAND t_user_problem_data.user_id =#{userId}")
    List<UserAnswerData> getWrongProblem(@Param("userId") String userId,@Param("trueFlag") Integer trueFlag);

    @Select("SELECT\n" +
            "\tt_user_problem_data.*,\n" +
            "\tt_chapter.chapter_name,\n" +
            "\tt_subject.subject_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.user_id =#{userId}")
    List<UserAnswerData> getAnswerProblem(@Param("userId") String userId);
}
