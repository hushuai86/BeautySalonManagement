package com.java.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.pojo.UserInfo;
import com.java.service.AccountService;
import com.java.service.UserInfoService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private AccountService accountService;
	//会员获取个人信息
		@RequestMapping("/getUserInfo")
		@ResponseBody
		public ResponseResult getUserInfo(HttpServletRequest request) {
			try {
				HttpSession session =request.getSession();
				UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, userInfoService.selectByPrimaryKey(userInfo.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}
		// 查看所有用户信息
		@RequestMapping("/selectUserInfoList")
		@ResponseBody
		public ResponseResult selectUserInfoList(UserInfo userInfo) {
			try {
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, accountService.selectUserInfoList(userInfo));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}

		}
		// 查看单个用户信息
				@RequestMapping("/selectUserInfo")
				@ResponseBody
				public ResponseResult selectUserInfo(String loginId) {
					try {
						Map<String,Object> map = new HashMap<>();
						map.put("loginId",loginId);
						if(accountService.selectAccount(map)==null){
							return ResponseResult.ok(false, DefaultErrMsg.USER_INFO_NOT_EXIST);
						}
						Integer accountId = accountService.selectAccount(map).getId();
						if(userInfoService.selectByAccountId(accountId)==null){
							return ResponseResult.ok(false, DefaultErrMsg.USER_INFO_NOT_EXIST);
						}
						return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, userInfoService.selectByAccountId(accountId));
					} catch (Exception e) {
						e.printStackTrace();
						return ResponseResult.ok(false, DefaultErrMsg.FAILD);
					}
				}
		// 修改会员信息
		@RequestMapping("/updateUserInfo")
		@ResponseBody
		public ResponseResult updateUserInfo(UserInfo userInfo) {
			try {
				userInfoService.updateByPrimaryKeySelective(userInfo);
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}

		}
		//余额充值
		@RequestMapping("/recharge")
		@ResponseBody
		public ResponseResult recharge(Integer id,Double recharge) {
			try {
				Double balance = userInfoService.selectByPrimaryKey(id).getBalance();
				UserInfo userInfo = new UserInfo();
				userInfo.setId(id);
				userInfo.setBalance(balance+recharge);
				userInfoService.updateByPrimaryKeySelective(userInfo);
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}

}
