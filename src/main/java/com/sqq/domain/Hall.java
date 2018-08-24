package com.sqq.domain;

public class Hall {
    private Integer hallid;

    private String hallcode;

    private String hallname;

    private Boolean vip;

    private Boolean imax;

    private Boolean couplehall;

    private String specialsjsonstring;

    private Integer cinemaid;

    private String createtime;

    private String updatetime;

    public Integer getHallid() {
        return hallid;
    }

    public void setHallid(Integer hallid) {
        this.hallid = hallid;
    }

    public String getHallcode() {
        return hallcode;
    }

    public void setHallcode(String hallcode) {
        this.hallcode = hallcode;
    }

    public String getHallname() {
        return hallname;
    }

    public void setHallname(String hallname) {
        this.hallname = hallname;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Boolean getImax() {
        return imax;
    }

    public void setImax(Boolean imax) {
        this.imax = imax;
    }

    public Boolean getCouplehall() {
        return couplehall;
    }

    public void setCouplehall(Boolean couplehall) {
        this.couplehall = couplehall;
    }

    public String getSpecialsjsonstring() {
        return specialsjsonstring;
    }

    public void setSpecialsjsonstring(String specialsjsonstring) {
        this.specialsjsonstring = specialsjsonstring;
    }

    public Integer getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Integer cinemaid) {
        this.cinemaid = cinemaid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}