package com.java.pojo;


public class HairdressingReservation {
    private Integer id;

    private String hairdresserInfoCode;

    private Integer hairdressingProject;

    private Integer appointmentStatus;

    private Integer evaluate;

    private String remarks;

    private String userReservation;
    
    private String submitAppointmentTime;
    
    private String acceptAppointmentTime;
    
    private String appointmentTime;
    
    private Integer serviceTime;
    
    private String type;
    
    private Integer price;
    
    private String payType;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHairdresserInfoCode() {
        return hairdresserInfoCode;
    }

    public void setHairdresserInfoCode(String hairdresserInfoCode) {
        this.hairdresserInfoCode = hairdresserInfoCode == null ? null : hairdresserInfoCode.trim();
    }

    public Integer getHairdressingProject() {
        return hairdressingProject;
    }

    public void setHairdressingProject(Integer hairdressingProject) {
        this.hairdressingProject = hairdressingProject;
    }

    public String getSubmitAppointmentTime() {
		return submitAppointmentTime;
	}

	public void setSubmitAppointmentTime(String submitAppointmentTime) {
		this.submitAppointmentTime = submitAppointmentTime;
	}

	public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getUserReservation() {
        return userReservation;
    }

    public void setUserReservation(String userReservation) {
        this.userReservation = userReservation == null ? null : userReservation.trim();
    }

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getAcceptAppointmentTime() {
		return acceptAppointmentTime;
	}

	public void setAcceptAppointmentTime(String acceptAppointmentTime) {
		this.acceptAppointmentTime = acceptAppointmentTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}