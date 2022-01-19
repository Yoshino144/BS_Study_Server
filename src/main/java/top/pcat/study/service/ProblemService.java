package top.pcat.study.service;

import org.springframework.stereotype.Service;
import top.pcat.study.domain.Problem;

import java.util.List;

@Service
public interface  ProblemService {

    List<Problem> getProblem(String subjectId, String chapterId);
}
