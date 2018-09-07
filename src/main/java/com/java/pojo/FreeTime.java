package com.java.pojo;

public class FreeTime {
    private Integer id;

    private String hairdresserCode;

    private Integer am8;

    private Integer am10;

    private Integer am12;

    private Integer pm2;

    private Integer pm4;

    private Integer pm6;

    private Integer pm8;

    private Integer status; // 空闲状态

    private Integer time; // 要改变状态的时间

    public FreeTime(Integer am8, Integer am10, Integer am12, Integer pm2,
	    Integer pm4, Integer pm6, Integer pm8, Integer status) {
	super();
	this.am8 = am8;
	this.am10 = am10;
	this.am12 = am12;
	this.pm2 = pm2;
	this.pm4 = pm4;
	this.pm6 = pm6;
	this.pm8 = pm8;
	this.status = status;
    }

    public FreeTime() {
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getHairdresserCode() {
	return hairdresserCode;
    }

    public void setHairdresserCode(String hairdresserCode) {
	this.hairdresserCode = hairdresserCode;
    }

    public Integer getAm8() {
	return am8;
    }

    public void setAm8(Integer am8) {
	this.am8 = am8;
    }

    public Integer getAm10() {
	return am10;
    }

    public void setAm10(Integer am10) {
	this.am10 = am10;
    }

    public Integer getAm12() {
	return am12;
    }

    public void setAm12(Integer am12) {
	this.am12 = am12;
    }

    public Integer getPm2() {
	return pm2;
    }

    public void setPm2(Integer pm2) {
	this.pm2 = pm2;
    }

    public Integer getPm4() {
	return pm4;
    }

    public void setPm4(Integer pm4) {
	this.pm4 = pm4;
    }

    public Integer getPm6() {
	return pm6;
    }

    public void setPm6(Integer pm6) {
	this.pm6 = pm6;
    }

    public Integer getPm8() {
	return pm8;
    }

    public void setPm8(Integer pm8) {
	this.pm8 = pm8;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public Integer getTime() {
	return time;
    }

    public void setTime(Integer time) {
	this.time = time;
    }

}