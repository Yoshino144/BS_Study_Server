package top.pcat.study.domain;


public class Chapter {

  private long chapterId;
  private String chapterName;
  private long subjectId;
  private long chapterSize;
  private long chapterVersion;
  private String founderChapter;
  private java.sql.Timestamp timeChapter;
  private long deleteChapter;


  public long getChapterId() {
    return chapterId;
  }

  public void setChapterId(long chapterId) {
    this.chapterId = chapterId;
  }


  public String getChapterName() {
    return chapterName;
  }

  public void setChapterName(String chapterName) {
    this.chapterName = chapterName;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getChapterSize() {
    return chapterSize;
  }

  public void setChapterSize(long chapterSize) {
    this.chapterSize = chapterSize;
  }


  public long getChapterVersion() {
    return chapterVersion;
  }

  public void setChapterVersion(long chapterVersion) {
    this.chapterVersion = chapterVersion;
  }


  public String getFounderChapter() {
    return founderChapter;
  }

  public void setFounderChapter(String founderChapter) {
    this.founderChapter = founderChapter;
  }


  public java.sql.Timestamp getTimeChapter() {
    return timeChapter;
  }

  public void setTimeChapter(java.sql.Timestamp timeChapter) {
    this.timeChapter = timeChapter;
  }


  public long getDeleteChapter() {
    return deleteChapter;
  }

  public void setDeleteChapter(long deleteChapter) {
    this.deleteChapter = deleteChapter;
  }

}
