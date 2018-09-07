package com.java.dao;

import com.java.pojo.FreeTime;

public interface FreeTimeMapper {
    FreeTime selectFreeTime(String hairdresserCode);

    FreeTime selectFreeTimeSelective(FreeTime FreeTime);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(FreeTime record);

    FreeTime selectByPrimaryKey(Integer id);

    int updateByHairdresserCodeSelective(FreeTime record);

    int updateByHairdresserCode(FreeTime record);

    void deleteByCode(String code);
}