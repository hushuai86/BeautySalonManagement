package com.java.dao;

import java.util.List;

import com.java.pojo.StoreInfo;

public interface StoreInfoMapper {

    List<StoreInfo> selectStoreInfo();

    int deleteByPrimaryKey(Integer id);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo storeInfo);
}