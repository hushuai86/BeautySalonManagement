package com.java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.FreeTimeService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

@Controller
@RequestMapping("/freeTime")

public class FreeTimeController {
	@Autowired
	private FreeTimeService freeTimeService;
	
	// 查看所有服务项目
		@RequestMapping("/selectItem")
		@ResponseBody
		public ResponseResult selectItem(String  code){
			try {
				return ResponseResult.ok(true, DefaultErrMsg.SUCCESS, freeTimeService.selectFreeTime(code));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}


}
