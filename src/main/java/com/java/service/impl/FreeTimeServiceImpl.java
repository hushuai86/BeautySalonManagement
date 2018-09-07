package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.FreeTimeMapper;
import com.java.pojo.FreeTime;
import com.java.service.FreeTimeService;


@Service("FreeTimeService")
public class FreeTimeServiceImpl implements FreeTimeService{
	@Autowired
	private FreeTimeMapper freeTimeMapper;

	@Override
	public FreeTime selectFreeTime(String hairdresserCode) {
		return freeTimeMapper.selectFreeTime(hairdresserCode);
	}

	@Override
	public FreeTime selectFreeTimeSelective(FreeTime FreeTime) {
		return freeTimeMapper.selectFreeTimeSelective(FreeTime);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FreeTime record) {
		return freeTimeMapper.insertSelective(record);
	}

	@Override
	public FreeTime selectByPrimaryKey(Integer id) {
		return freeTimeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByHairdresserCodeSelective(FreeTime record) {
		return freeTimeMapper.updateByHairdresserCodeSelective(record);
	}

	@Override
	public int updateByHairdresserCode(FreeTime record) {
		return freeTimeMapper.updateByHairdresserCode(record);
	}
	
	//修改对应空闲状态
	@Override
	public void updateFreeTimeSelective(FreeTime freeTime) {
	    Integer serviceStartTime = freeTime.getTime();
	    Integer status = freeTime.getStatus();
		// 修改 FreeTime表 将对应时间设置为1 表示改时间已被预约, 0表示空闲
		if (serviceStartTime == 8) {
			freeTime.setAm8(status);
		}
		if (serviceStartTime == 10) {
			freeTime.setAm10(status);
		}
		if (serviceStartTime == 12) {
			freeTime.setAm12(status);
		}
		if (serviceStartTime == 14) {
			freeTime.setPm2(status);
		}
		if (serviceStartTime == 16) {
			freeTime.setPm4(status);
		}
		if (serviceStartTime == 18) {
			freeTime.setPm6(status);
		}
		if (serviceStartTime == 20) {
			freeTime.setPm8(status);
		}
		freeTimeMapper.updateByHairdresserCodeSelective(freeTime);
	    	}	    

	@Override
	public void updateFreeTime(FreeTime freeTime) {
		freeTimeMapper.updateByHairdresserCode(freeTime);
	    	}

	@Override
	public void deleteByCode(String code) {
	   freeTimeMapper.deleteByCode(code);
	    
	}	    
}
