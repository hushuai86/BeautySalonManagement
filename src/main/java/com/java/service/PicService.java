package com.java.service;

import java.util.List;

import com.java.pojo.Pic;

public interface PicService {
	public void insertSelective(Pic pic);

	public List<Pic> selectPicList();

	public byte[] selectPicStream(Integer id);

	public void updateByPrimaryKeySelective(Pic pic);

	public void deleteByPrimaryKey(Integer id);
}
