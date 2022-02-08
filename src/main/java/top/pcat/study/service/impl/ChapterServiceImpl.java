package top.pcat.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ChapterDao;
import top.pcat.study.domain.Chapter;
import top.pcat.study.service.ChapterService;

import java.util.List;
@Transactional
@Slf4j
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterDao chapterDao;
    @Override
    public List<Chapter> getChapterById(String subjectId){
        return chapterDao.getChapterById(subjectId);
    }
}
