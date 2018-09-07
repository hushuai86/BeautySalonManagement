package com.java.dao;

import com.java.pojo.UserInfo;

public interface UserInfoMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(UserInfo userInfo);

	UserInfo selectByPrimaryKey(Integer id);
	
	UserInfo selectByPhone(String phone);

	int updateByPrimaryKeySelective(UserInfo userInfo);
	
	int updateByPrimaryKey(UserInfo record);

	UserInfo selectByAccountId(int accountId);

	void deleteByAccountId(Integer accountId);

}