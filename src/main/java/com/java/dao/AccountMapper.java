package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.pojo.HairdresserInfo;
import com.java.pojo.UserInfo;
import com.java.pojo.Account;

public interface AccountMapper {
    Account selectAccount(Map<String, Object> map);

    List<Account> selectAllAccount(Account account);

    List<Map<String, Object>> selectHairdresser(HairdresserInfo hairdresserInfo);

    List<Map<String, Object>> selectUser(UserInfo userInfo);

    int deleteByPrimaryKey(Integer id);

    int insert(Account account);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

}