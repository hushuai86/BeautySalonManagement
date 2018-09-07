package com.java.dao;

import java.util.List;

import com.java.pojo.Code;

public interface CodeMapper {

    List<Code> selectItem();

    int deleteByPrimaryKey(Code code);

    int insert(Code record);

    int insertSelective(Code code);

    Code selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Code code);

    int updateByPrimaryKey(Code code);

    Code selectByName(String name);

}