package com.java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.pojo.StoreInfo;
import com.java.service.StoreInfoService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

@Controller
@RequestMapping("/storeInfo")
public class StoreInfoController {
	@Autowired
	private StoreInfoService storeInfoService;

	// 获取店铺信息
	@RequestMapping("/selectStoreInfo")
	@ResponseBody
	public ResponseResult selectStoreInfo() {
		return ResponseResult.ok(true, DefaultErrMsg.SUCCESS, storeInfoService.selectStoreInfo());
	}

	// 更新店铺信息
	@RequestMapping("/updateStoreInfo")
	@ResponseBody
	public ResponseResult updateStoreInfo(StoreInfo storeInfo) {
		try {
			storeInfoService.updateStoreInfo(storeInfo);
			return ResponseResult.ok(true, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}

}
