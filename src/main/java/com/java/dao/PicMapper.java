package com.java.dao;

import java.util.List;

import com.java.pojo.Pic;

public interface PicMapper {
    List<Pic> selectPicList();

    Pic selectPicStream(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic pic);

    Pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pic pic);

    int updateByPrimaryKeyWithBLOBs(Pic record);

    int updateByPrimaryKey(Pic record);
}