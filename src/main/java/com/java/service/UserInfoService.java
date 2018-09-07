package com.java.service;


import com.java.pojo.UserInfo;

public interface UserInfoService {
	
	public void insert(UserInfo UserInfo);
	
	public void updateByPrimaryKeySelective(UserInfo userInfo);
	
	public UserInfo selectByPrimaryKey(Integer id);
	
	public UserInfo selectByPhone(String phone);

	public UserInfo selectByAccountId(int accountId);

	public void deleteByAccountId(Integer accountId);

}
