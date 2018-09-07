package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.UserInfoMapper;
import com.java.pojo.UserInfo;
import com.java.service.UserInfoService;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public void insert(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}

	@Override
	public void updateByPrimaryKeySelective(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public UserInfo selectByPrimaryKey(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserInfo selectByPhone(String phone) {
		return userInfoMapper.selectByPhone(phone);
	}

	@Override
	public UserInfo selectByAccountId(int accountId) {
		return userInfoMapper.selectByAccountId(accountId);
	}

	@Override
	public void deleteByAccountId(Integer accountId) {
		userInfoMapper.deleteByAccountId(accountId);
		
	}

}
