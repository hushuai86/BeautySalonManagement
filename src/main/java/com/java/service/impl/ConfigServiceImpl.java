package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.ConfigMapper;
import com.java.pojo.Config;
import com.java.service.ConfigService;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigMapper configMapper;

	@Override
	public void insertSelective(Config config) {
		configMapper.insertSelective(config);
	}

	@Override
	public List<Config> selectConfigList() {
		return configMapper.selectConfigList();
	}


	@Override
	public void updateByPrimaryKeySelective(Config config) {
		configMapper.updateByPrimaryKeySelective(config);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		configMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String selectConfigValueByName(String name) {
		return configMapper.selectConfigValueByName(name);
	}

	@Override
	public Config selectByPrimaryKey(Integer id) {
		return configMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insert(Config config) {
		configMapper.insert(config);
	}

	@Override
	public void updateByPrimaryKey(Config config) {
		configMapper.updateByPrimaryKey(config);
	}

	@Override
	public void deleteByName(String name) {
		configMapper.deleteByName(name);
	}

	@Override
	public void updateValueByName(Config config) {
		configMapper.updateValueByName(config);
	}
}
