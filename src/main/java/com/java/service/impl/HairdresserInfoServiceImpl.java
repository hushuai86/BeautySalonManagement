package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.HairdresserInfoMapper;
import com.java.pojo.HairdresserInfo;
import com.java.service.HairdresserInfoService;

@Service("HairdresserInfoService")
public class HairdresserInfoServiceImpl implements HairdresserInfoService {
	@Autowired
	private HairdresserInfoMapper hairdresserInfoMapper;

	@Override
	public void insert(HairdresserInfo hairdresserInfo) {
		hairdresserInfoMapper.insert(hairdresserInfo);
	}

	@Override
	public void updateByPrimaryKeySelective(HairdresserInfo hairdresserInfo) {
		hairdresserInfoMapper.updateByPrimaryKeySelective(hairdresserInfo);
	}

	@Override
	public List<HairdresserInfo> selectCharge(HairdresserInfo hairdresserInfo) {
		return hairdresserInfoMapper.selectCharge(hairdresserInfo);
	}

	@Override
	public byte[] selectPicStream(Integer id) {
		return hairdresserInfoMapper.selectPicStream(id).getPicUrl();
	}

	@Override
	public HairdresserInfo selectByCode(String code) {
		return hairdresserInfoMapper.selectByCode(code);
	}

	@Override
	public String getNextCode() {
		List<HairdresserInfo> hairdresserInfos = hairdresserInfoMapper.selectCharge(new HairdresserInfo());
			int len = 4;
			String lastCode =hairdresserInfos.get(hairdresserInfos.size()-1).getCode();
			if(lastCode!=null){
				len = lastCode.length();
			}
			int codeNum = Integer.parseInt(lastCode);
			if(String.valueOf(codeNum).length()==len){
				return String.valueOf(codeNum+1); 
		}
			String nextCode="";
			for(int i=0;i<len-String.valueOf(codeNum+1).length();i++){
				nextCode+="0";
			}
			nextCode+=String.valueOf(codeNum+1);
			return nextCode ;		
	}

	@Override
	public HairdresserInfo selectByAccountId(Integer accountId) {
		return hairdresserInfoMapper.selectByAccountId(accountId);
	}

	@Override
	public HairdresserInfo selectByPhone(String phone) {
		return hairdresserInfoMapper.selectByPhone(phone);
	}

	@Override
	public void deleteByAccountId(Integer accountId) {
		hairdresserInfoMapper.deleteByAccountId(accountId);
	}

}
