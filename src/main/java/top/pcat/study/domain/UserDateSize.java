package top.pcat.study.domain;


import java.util.Date;

public class UserDateSize {

  private String userId;
  private Date date;
  private long size;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

}
