package com.java.service;

import java.util.List;

import com.java.pojo.HairdresserInfo;

public interface HairdresserInfoService {
	
	public void insert(HairdresserInfo hairdresserInfo);
	
	public void updateByPrimaryKeySelective(HairdresserInfo hairdresserInfo);
	
	public List<HairdresserInfo> selectCharge(HairdresserInfo hairdresserInfo);
	
	public byte[] selectPicStream(Integer id);
	
	public HairdresserInfo selectByCode(String code);

	public String getNextCode();

	public HairdresserInfo selectByAccountId(Integer accountId);
	
	public  HairdresserInfo selectByPhone(String phone);

	public void deleteByAccountId(Integer accountId);
		
}
