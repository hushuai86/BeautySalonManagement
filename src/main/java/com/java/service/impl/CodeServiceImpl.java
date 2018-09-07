package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CodeMapper;
import com.java.pojo.Code;
import com.java.service.CodeService;

@Service("CodeService")
public class CodeServiceImpl implements CodeService {
	@Autowired
	private CodeMapper codeMapper;

	@Override
	public List<Code> selectItem() {
		return codeMapper.selectItem();
	}

	@Override
	public void insertSelective(Code code) {
		codeMapper.insertSelective(code);
	}

	@Override
	public void updateByPrimaryKeySelective(Code code) {
		codeMapper.updateByPrimaryKeySelective(code);
	}

	@Override
	public void deleteByPrimaryKey(Code code) {
		codeMapper.deleteByPrimaryKey(code);
	}

	@Override
	public Code selectByPrimaryKey(Integer id) {
		return codeMapper.selectByPrimaryKey(id);
	}

	@Override
	public Code selectByName(String name) {
		return codeMapper.selectByName(name);
	}

}
