package top.pcat.study.service;

import top.pcat.study.domain.WrongProblem;

import java.util.List;

public interface UserDateService {
    List<WrongProblem> getWrongProblem(String userId, String trueFlag);
    long getTodaySize(String userId);
    String getWeekSize(String userId);
    String getSize(String userId);
}
