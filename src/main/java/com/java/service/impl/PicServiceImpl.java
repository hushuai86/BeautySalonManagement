package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PicMapper;
import com.java.pojo.Pic;
import com.java.service.PicService;

@Service("PicService")
public class PicServiceImpl implements PicService {
	@Autowired
	private PicMapper picMapper;

	@Override
	public void insertSelective(Pic pic) {
		// TODO Auto-generated method stub
		picMapper.insertSelective(pic);
	}

	@Override
	public List<Pic> selectPicList() {
		// TODO Auto-generated method stub
		return picMapper.selectPicList();
	}

	@Override
	public byte[] selectPicStream(Integer id) {
		// TODO Auto-generated method stub
		return picMapper.selectPicStream(id).getPhoto();
	}

	@Override
	public void updateByPrimaryKeySelective(Pic pic) {
		// TODO Auto-generated method stub
		picMapper.updateByPrimaryKeySelective(pic);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		picMapper.deleteByPrimaryKey(id);
	}
}
