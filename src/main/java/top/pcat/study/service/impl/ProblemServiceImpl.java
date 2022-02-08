package top.pcat.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ProblemDao;
import top.pcat.study.domain.Problem;
import top.pcat.study.service.ProblemService;

import java.util.List;
@Transactional
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemDao problemDao;

    @Override
    public List<Problem> getProblem(String subjectId, String chapterId) {
        return  this.problemDao.selectByExample(subjectId,chapterId);
    }
}
