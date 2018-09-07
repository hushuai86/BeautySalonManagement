package com.java.service;

import java.util.List;


import com.java.pojo.Code;

public interface CodeService {
	public List<Code> selectItem();

	public void insertSelective(Code code);
	
	public void updateByPrimaryKeySelective(Code code);
	
	public void deleteByPrimaryKey(Code code);
	
	Code selectByPrimaryKey(Integer id);
	
	public Code selectByName(String name);
}
