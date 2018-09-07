package com.java.util;



public class DefaultErrMsg {
	public static final String[] SUCCESS = { "200", "成功！" };
	public static final String[] USER_LOGIN_SUCCESS = { "501", "登录成功！" };
	public static final String[] USER_LOGOUT_SUCCESS = { "502", "注销成功！" };
	public static final String[] FAILD = { "100", "失败！" };
	public static final String[] USER_UNREGISTERED = { "101", "用户未注册！" };
	public static final String[] USER_REGISTERED = { "102", "用户已注册！" };
	public static final String[] USER_NOT_EXISTS = { "103", "用户不存在！" };

	public static final String[] PHONE_NUM_INCORRECT = { "104", "手机号码不正确！" };
	public static final String[] PHONE_PASSWORD_ISNULL = { "105", "账户或密码为空！" };
	public static final String[] USER_LOGIN_FAILD = { "106", "密码错误！" };

	// 用户信息
	public static final String[] USER_INFO_EXIST = { "122", "用户信息已存在!" };
	public static final String[] USER_INFO_NOT_EXIST = { "123", "用户信息不存在!!" };
	public static final String[] USER_NOT_EXIST = { "124", "该会员已被删除!" };
	public static final String[] HAIRDRESSER_NOT_EXIST = { "125", "美发师信息不存在!！" };


	// 修改密码失败
	public static final String[] USER_CHANGE_PASSWORD_FAILD = { "126", "新密码不能和原密码相同！" };
	public static final String[] USER_PASSWORD_FAILD = { "127", "原密码错误！" };

	// 获取用户信息失败
	public static final String[] USER_INFO_FALSE = { "128", "获取用户信息失败！" };
	// 没有数据
	public static final String[] NO_DATA = { "130", "没有数据！" };

	// token失效 登录失败
	public static final String[] USER_TOKEN_INVALID = { "401", "登录失效!" };

	public static final String[] CONTENT_NOT_EXIST = { "942", "没有获取到数据！" };

	public static final String[] APP_NOT_EXIST = { "950", "用戶未激活" };

	// 输入内容不可以为空
	public static final String[] USER_VALUE_NULL = { "141", "输入内容不可以为空!" };
	
	public static final String[] HAVE_RESERVATION = { "143", "该理发师当天已经有预约，请重新选择!" };
	public static final String[] HAVE_USER= { "144", "该帐号已经注册，请重新输入帐号!" };
	public static final String[] HAVE_PHONE= { "146", "该手机号已被注册，请重新输入手机号!" };
	public static final String[] HAVE_CODE= { "147", "服务项目名已存在！" };
	
	public static final String[] BALANCE_NOT_ENOUGH_FOR_APPOINTMENT= { "148", "余额不足，无法预约！" };
	
	public static final String[] BALANCE_NOT_ENOUGH_FOR_COMPLETE= { "149", "余额不足，无法余额支付！" };
}