package com.java.pojo;

public class Account {
    private Integer id;

    private String loginId;

    private String password;

    private Integer type;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		 this.loginId = loginId == null ? null : loginId.trim();
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}