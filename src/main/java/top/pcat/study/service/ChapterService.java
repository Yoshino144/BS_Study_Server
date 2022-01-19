package top.pcat.study.service;

import org.springframework.stereotype.Service;
import top.pcat.study.domain.Chapter;

import java.util.List;

@Service
public interface ChapterService {
    List<Chapter> getChapterById(String subjectId);
}
