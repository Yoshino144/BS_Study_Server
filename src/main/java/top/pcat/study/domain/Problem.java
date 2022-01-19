package top.pcat.study.domain;


public class Problem {

  private long problemId;
  private String problemContent;
  private String problemType;
  private String problemAnswer;
  private String A;
  private String B;
  private String C;
  private String D;
  private String problemAnalysis;
  private long chapterId;
  private String founderSubject;
  private java.sql.Timestamp timeSubject;
  private long deleteSubject;


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public String getProblemContent() {
    return problemContent;
  }

  public void setProblemContent(String problemContent) {
    this.problemContent = problemContent;
  }


  public String getProblemType() {
    return problemType;
  }

  public void setProblemType(String problemType) {
    this.problemType = problemType;
  }


  public String getProblemAnswer() {
    return problemAnswer;
  }

  public void setProblemAnswer(String problemAnswer) {
    this.problemAnswer = problemAnswer;
  }


  public String getA() {
    return A;
  }

  public void setA(String A) {
    this.A = A;
  }


  public String getB() {
    return B;
  }

  public void setB(String B) {
    this.B = B;
  }


  public String getC() {
    return C;
  }

  public void setC(String C) {
    this.C = C;
  }


  public String getD() {
    return D;
  }

  public void setD(String D) {
    this.D = D;
  }


  public String getProblemAnalysis() {
    return problemAnalysis;
  }

  public void setProblemAnalysis(String problemAnalysis) {
    this.problemAnalysis = problemAnalysis;
  }


  public long getChapterId() {
    return chapterId;
  }

  public void setChapterId(long chapterId) {
    this.chapterId = chapterId;
  }


  public String getFounderSubject() {
    return founderSubject;
  }

  public void setFounderSubject(String founderSubject) {
    this.founderSubject = founderSubject;
  }


  public java.sql.Timestamp getTimeSubject() {
    return timeSubject;
  }

  public void setTimeSubject(java.sql.Timestamp timeSubject) {
    this.timeSubject = timeSubject;
  }


  public long getDeleteSubject() {
    return deleteSubject;
  }

  public void setDeleteSubject(long deleteSubject) {
    this.deleteSubject = deleteSubject;
  }

}
