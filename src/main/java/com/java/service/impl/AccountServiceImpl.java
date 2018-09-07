package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.AccountMapper;
import com.java.pojo.HairdresserInfo;
import com.java.pojo.UserInfo;
import com.java.pojo.Account;
import com.java.service.AccountService;


@Service("AccountService")
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account selectAccount(Map<String, Object> map) {
		return  accountMapper.selectAccount(map);
	}

	@Override
	public Integer insert(Account account) {
		return accountMapper.insert(account);
	}

	@Override
	public List<Map<String, Object>> selectHairdresser(HairdresserInfo hairdresserInfo) {
		return accountMapper.selectHairdresser( hairdresserInfo);
	}

	@Override
	public void updateAccount(Account account) {
		accountMapper.updateByPrimaryKey(account);
	}

	@Override
	public List<Account> selectAllAccount(Account account) {
		return accountMapper.selectAllAccount(account);
	}

	@Override
	public int updateByPrimaryKeySelective(Account record) {
		return accountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map<String, Object>> selectUserInfoList(UserInfo userInfo) {
		return accountMapper.selectUser(userInfo);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		
		accountMapper.deleteByPrimaryKey(id);
	}

}
