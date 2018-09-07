package com.java.dao;

import java.util.List;

import com.java.pojo.HairdresserInfo;

public interface HairdresserInfoMapper {

    List<HairdresserInfo> selectCharge(HairdresserInfo hairdresserInfo);

    int deleteByPrimaryKey(Integer id);

    int insert(HairdresserInfo hairdresserInfo);

    int insertSelective(HairdresserInfo record);

    HairdresserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HairdresserInfo hairdresserInfo);

    HairdresserInfo selectByCode(String code);

    HairdresserInfo selectPicStream(Integer id);

    int updateByPrimaryKey(HairdresserInfo record);

    HairdresserInfo selectByAccountId(Integer accountId);

    HairdresserInfo selectByPhone(String phone);

    void deleteByAccountId(Integer accountId);

}