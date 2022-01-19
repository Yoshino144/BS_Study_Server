package top.pcat.study.domain;

import java.util.Date;

public class Yixuan {
    private String idSubject;
    private String nameSubject;
    private Date chooseTime;
    private String founderSubject;
    private Date timeSubject;

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Date getChooseTime() {
        return chooseTime;
    }

    public void setChooseTime(Date chooseTime) {
        this.chooseTime = chooseTime;
    }

    public String getFounderSubject() {
        return founderSubject;
    }

    public void setFounderSubject(String founderSubject) {
        this.founderSubject = founderSubject;
    }

    public Date getTimeSubject() {
        return timeSubject;
    }

    public void setTimeSubject(Date timeSubject) {
        this.timeSubject = timeSubject;
    }

    public long getPrivateSubject() {
        return privateSubject;
    }

    public void setPrivateSubject(long privateSubject) {
        this.privateSubject = privateSubject;
    }

    public long getSizeSubject() {
        return sizeSubject;
    }

    public void setSizeSubject(long sizeSubject) {
        this.sizeSubject = sizeSubject;
    }

    public long getOfficialSubject() {
        return officialSubject;
    }

    public void setOfficialSubject(long officialSubject) {
        this.officialSubject = officialSubject;
    }

    public long getAdminSubject() {
        return adminSubject;
    }

    public void setAdminSubject(long adminSubject) {
        this.adminSubject = adminSubject;
    }

    private long privateSubject;
    private long sizeSubject;
    private long officialSubject;
    private long adminSubject;
}
