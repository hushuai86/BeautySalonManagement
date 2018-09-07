package com.java.service;

import java.util.List;

import com.java.pojo.StoreInfo;

public interface StoreInfoService {
	public List<StoreInfo> selectStoreInfo();
	public void updateStoreInfo(StoreInfo storeInfo);
}
