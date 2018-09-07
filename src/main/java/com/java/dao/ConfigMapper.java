package com.java.dao;

import java.util.List;

import com.java.pojo.Config;

public interface ConfigMapper {
    void insertSelective(Config config);

    void updateByPrimaryKeySelective(Config config);

    List<Config> selectConfigList();

    String selectConfigValueByName(String name);

    Config selectByPrimaryKey(Integer id);

    void insert(Config config);

    void updateByPrimaryKey(Config config);

    void deleteByName(String name);

    void deleteByPrimaryKey(Integer id);

    int updateValueByName(Config config);
}
