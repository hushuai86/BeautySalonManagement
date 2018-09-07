package com.java.service;

import java.util.List;

import com.java.pojo.Config;

public interface ConfigService {
	public void insertSelective(Config config);

	public void updateByPrimaryKeySelective(Config config);

	public List<Config> selectConfigList();

	public String selectConfigValueByName(String name);

	public Config selectByPrimaryKey(Integer id);

	public void insert(Config config);

	public void updateByPrimaryKey(Config config);

	public void deleteByName(String name);

	public void deleteByPrimaryKey(Integer id);
	
	public void updateValueByName(Config config);
}
