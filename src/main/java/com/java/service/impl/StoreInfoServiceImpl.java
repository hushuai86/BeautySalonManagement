package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.StoreInfoMapper;
import com.java.pojo.StoreInfo;
import com.java.service.StoreInfoService;

@Service("StoreInfoService")
public class StoreInfoServiceImpl implements StoreInfoService {
	@Autowired
	private StoreInfoMapper storeInfoMapper;

	public List<StoreInfo> selectStoreInfo() {
		return storeInfoMapper.selectStoreInfo();
	}

	@Override
	public void updateStoreInfo(StoreInfo storeInfo) {
		storeInfoMapper.updateByPrimaryKey(storeInfo);

	}

}
