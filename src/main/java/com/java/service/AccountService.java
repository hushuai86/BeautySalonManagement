package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.pojo.HairdresserInfo;
import com.java.pojo.UserInfo;
import com.java.pojo.Account;

public interface AccountService {
	Account selectAccount(Map<String, Object> map);

	public Integer insert(Account account);

	public List<Map<String, Object>> selectHairdresser(HairdresserInfo hairdresserInfo);

	public void updateAccount(Account account);

	public List<Account> selectAllAccount(Account account);

	int updateByPrimaryKeySelective(Account record);

	List<Map<String, Object>> selectUserInfoList(UserInfo userInfo);

	void deleteByPrimaryKey(Integer accountId);
}
