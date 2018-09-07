package com.java.pojo;

public class HairdresserInfo {
    private Integer id;

    private Integer accountId;

    private String code;

    private byte[] picUrl;

    private String name;
    
    private String sex;

    private String dateOfEntry;

    private String phone;
    
    private Integer baseCharge;
    
    private Double commission;
    
    private Integer hairdresserStatus;

    public Integer getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(Integer baseCharge) {
		this.baseCharge = baseCharge;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

  
    public byte[] getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(byte[] picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(String dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

	public Integer getHairdresserStatus() {
		return hairdresserStatus;
	}

	public void setHairdresserStatus(Integer hairdresserStatus) {
		this.hairdresserStatus = hairdresserStatus;
	}
    
}