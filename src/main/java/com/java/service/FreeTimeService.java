package com.java.service;

import com.java.pojo.FreeTime;

public interface FreeTimeService {
	FreeTime selectFreeTime(String hairdresserCode);

	public FreeTime selectFreeTimeSelective(FreeTime FreeTime);

	int deleteByPrimaryKey(Integer id);

	int insertSelective(FreeTime record);

	FreeTime selectByPrimaryKey(Integer id);

	int updateByHairdresserCodeSelective(FreeTime record);

	int updateByHairdresserCode(FreeTime record);

	void updateFreeTimeSelective(FreeTime freeTime);
	
	void deleteByCode(String code);

	void updateFreeTime(FreeTime freeTime);

}
