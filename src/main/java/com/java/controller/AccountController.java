package com.java.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.pojo.Account;
import com.java.pojo.FreeTime;
import com.java.pojo.HairdresserInfo;
import com.java.pojo.HairdressingReservation;
import com.java.pojo.UserInfo;
import com.java.service.FreeTimeService;
import com.java.service.HairdresserInfoService;
import com.java.service.HairdressingReservationService;
import com.java.service.UserInfoService;
import com.java.service.AccountService;
import com.java.util.DefaultErrMsg;
import com.java.util.MD5Password;
import com.java.util.ResponseResult;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HairdresserInfoService hairdresserInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FreeTimeService freeTimeService;
    @Autowired
    private HairdressingReservationService hairdressingReservationService;

    private Runnable runnable;

    // 登录
    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(Account account, HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("loginId", account.getLoginId());
	    String password = MD5Password.putPasswordMD5(account.getPassword());
	    account = accountService.selectAccount(map);
	    if (account != null && account.getPassword() != null
		    && account.getPassword().equals(password)) {
		if (account.getStatus().equals(0)) {// 用戶未激活
		    return ResponseResult.ok(false,
			    DefaultErrMsg.APP_NOT_EXIST, account);
		} else {
		    HttpSession session = request.getSession();
		    session.setAttribute("loginId", account.getLoginId());
		    session.setAttribute("account", account);
		    if (account.getType() == 1) {// 会员
			UserInfo userInfo = userInfoService
				.selectByAccountId(account.getId());
			session.setAttribute("userInfo", userInfo);
		    } else if (account.getType() == 2) {// 美发师
			HairdresserInfo hairdresserInfo = hairdresserInfoService
				.selectByAccountId(account.getId());
			session.setAttribute("hairdresserInfo", hairdresserInfo);
		    }
		    scheduledResertHairdresserStatus();
		    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS,account);
		}

	    } else {
		return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 定时任务 重置美发师的空闲时间
    private void scheduledResertHairdresserStatus() {
	if (runnable == null) {
	    //首次登陆执行一次。
	    resertHairdresserStatus();
	    runnable = new Runnable() {
		public void run() {
		    resertHairdresserStatus();
		}
	};
	    ScheduledExecutorService service = Executors .newSingleThreadScheduledExecutor();
	    Calendar todayCal = Calendar.getInstance();
	    todayCal.set(Calendar.DATE, todayCal.get(Calendar.DATE));
	    Calendar twmCal = Calendar.getInstance();
	    twmCal.set(Calendar.DATE, twmCal.get(Calendar.DATE) + 1);
	    twmCal.set(Calendar.HOUR_OF_DAY, 0);
	    twmCal.set(Calendar.MINUTE, 0);
	    twmCal.set(Calendar.SECOND, 0);
	    twmCal.set(Calendar.MILLISECOND, 0);
		//当前时间
		Date today = todayCal.getTime();
		//明天的零点
		Date twmZero = twmCal.getTime();
		//计算距离下个零点的分钟数
		long  delay = (twmZero.getTime() -today.getTime())/(1000*60);
		//从下个零点开始执行，执行间隔24小时
	    // 参数：1、任务体 2、首次执行的延时时间 3、任务执行间隔 4、间隔时间单位
 	    service.scheduleAtFixedRate(runnable,delay, 24*60, TimeUnit.MINUTES);
       }
    }
    
     private void resertHairdresserStatus(){
	 //获取今天的 日期和 明天的日期格式化
	    Calendar twmCal = Calendar.getInstance();
	    twmCal.set(Calendar.DATE, twmCal.get(Calendar.DATE)+1);
	    Date  twmDate = twmCal.getTime();
	    SimpleDateFormat daydf = new SimpleDateFormat("yyyy-MM-dd");
	    //查找明天的订单
	    List<HairdressingReservation> twmReservations = hairdressingReservationService. selectAllReservationsByDay(daydf.format(twmDate));
	    List<String> codes =  new ArrayList<String>();
	    
	    //查询所有理发师的编号
	    List<HairdresserInfo> hfs = hairdresserInfoService.selectCharge(new HairdresserInfo());
	    for (HairdresserInfo hairdresserInfo : hfs) {
		codes.add(hairdresserInfo.getCode());
	    }
	    for (String code : codes) {
	        FreeTime  freetime = new FreeTime(0,0,0,0,0,0,0,0);
		//如果明天没预约将理发师的对应的预约时间状态改为空闲
		    for(HairdressingReservation twmR: twmReservations){
			 if(twmR.getHairdresserInfoCode().equals(code)){
			     Integer hour = Integer.parseInt(twmR.getAppointmentTime().split(" ")[1].split(":")[0]);
				 updateFreeTime(freetime,hour,1);
			    }
		    }
		if(!freetime.equals(new FreeTime(0,0,0,0,0,0,0,0))){
		    freetime.setHairdresserCode(code);
		    freeTimeService.updateFreeTime(freetime);
		}
	    }
     }
     private void updateFreeTime(FreeTime freeTime,Integer time,Integer status) {
		// 修改 FreeTime表 将对应时间设置为1 表示改时间已被预约, 0表示空闲
		if (time == 8) {
			freeTime.setAm8(status);
		}
		if (time == 10) {
			freeTime.setAm10(status);
		}
		if (time == 12) {
			freeTime.setAm12(status);
		}
		if (time == 14) {
			freeTime.setPm2(status);
		}
		if (time == 16) {
			freeTime.setPm4(status);
		}
		if (time == 18) {
			freeTime.setPm6(status);
		}
		if (time == 20) {
			freeTime.setPm8(status);
		}
     }
     
    // 注册会员
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult registerUser(Account account, UserInfo userInfo) {
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("loginId", account.getLoginId());
	    // 查看账号是否注册
	    if (accountService.selectAccount(map) != null) {
		return ResponseResult.ok(false, DefaultErrMsg.HAVE_USER);
	    }
	    // 查看手机号是否注册
	    else if (userInfoService.selectByPhone(userInfo.getPhone()) != null
		    || hairdresserInfoService
			    .selectByPhone(userInfo.getPhone()) != null) {
		return ResponseResult.ok(false, DefaultErrMsg.HAVE_PHONE);
	    } else {
		account.setType(1);
		account.setStatus(1);
		account.setPassword(MD5Password.putPasswordMD5(account
			.getPassword()));
		accountService.insert(account);
		Integer accountId = accountService.selectAccount(map).getId();
		userInfo.setAccountId(accountId);
		userInfoService.insert(userInfo);
		return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 注册理发师
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult register(Account account,
	    HairdresserInfo hairdresserInfo,
	    @RequestParam(value = "file", required = false) MultipartFile file) {
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("loginId", account.getLoginId());
	    // 查看账号是否注册
	    if (accountService.selectAccount(map) != null) {
		return ResponseResult.ok(false, DefaultErrMsg.HAVE_USER);
	    } // 查看手机号是否注册
	    else if (userInfoService.selectByPhone(hairdresserInfo.getPhone()) != null
		    || hairdresserInfoService.selectByPhone(hairdresserInfo
			    .getPhone()) != null) {
		return ResponseResult.ok(false, DefaultErrMsg.HAVE_PHONE);
	    } else {
		account.setType(2);
		account.setStatus(0);
		account.setPassword(MD5Password.putPasswordMD5(account
			.getPassword()));
		accountService.insert(account);
		account = accountService.selectAccount(map);
		hairdresserInfo.setAccountId(account.getId());
		// 生成美发师编号
		String code = hairdresserInfoService.getNextCode();
		hairdresserInfo.setCode(code);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// 理发师注册日期为当前日期
		hairdresserInfo.setDateOfEntry(df.format(date).toString());
		if (file != null && !file.isEmpty() && file.getSize() > 0) {
		    hairdresserInfo.setPicUrl(file.getBytes());
		}
		hairdresserInfoService.insert(hairdresserInfo);
		// 空闲时间表插入信息
		FreeTime freeTime = new FreeTime();
		freeTime.setHairdresserCode(code);
		freeTimeService.insertSelective(freeTime);
		return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 删除美发师信息
    @RequestMapping("/deleteHairdresser")
    @ResponseBody
    public ResponseResult deleteHairdresser(Integer accountId) {
	try {
	    String code = hairdresserInfoService.selectByAccountId(accountId).getCode();
	    hairdresserInfoService.deleteByAccountId(accountId);
	    accountService.deleteByPrimaryKey(accountId);
	    freeTimeService.deleteByCode(code);
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}
    }

    // 删除用户信息
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResponseResult deleteUser(Integer accountId) {
	try {
	    userInfoService.deleteByAccountId(accountId);
	    accountService.deleteByPrimaryKey(accountId);
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}
    }

    // 获取当前session 账号信息
    @RequestMapping("/getSessionAccount")
    @ResponseBody
    public ResponseResult getSessionAccount(HttpServletRequest request) {
	try {
	    HttpSession session = request.getSession();
	    Account account = (Account) session.getAttribute("account");
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, account);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 修改用户状态
    @RequestMapping("/updateInfo")
    @ResponseBody
    public ResponseResult updateInfo(Account account) {
	try {
	    if (account != null) {
		accountService.updateByPrimaryKeySelective(account);
	    }
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 修改理发师信息
    @RequestMapping("/updateInfo2")
    @ResponseBody
    public ResponseResult updateInfo2(HairdresserInfo hairdresserInfo) {
	try {
	    hairdresserInfoService.updateByPrimaryKeySelective(hairdresserInfo);
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}
	//
    }

    // 修改密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResponseResult updatePassword(Account account,
	    HttpServletRequest request) {
	try {
	    String password = MD5Password.putPasswordMD5(request
		    .getParameter("oldPassword"));
	    account.setPassword(MD5Password.putPasswordMD5(account
		    .getPassword()));
	    Account sessionaAccount = (Account) request.getSession()
		    .getAttribute("account");
	    if (!sessionaAccount.getPassword().equals(password)) {
		return ResponseResult.ok(false,
			DefaultErrMsg.USER_PASSWORD_FAILD);
	    } else if (password.equals(account.getPassword())) {
		return ResponseResult.ok(false,
			DefaultErrMsg.USER_CHANGE_PASSWORD_FAILD);
	    }
	    accountService.updateByPrimaryKeySelective(account);
	    request.getSession().invalidate();
	    return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    return ResponseResult.ok(false, DefaultErrMsg.FAILD);
	}

    }

    // 注销
    // 退出
    @RequestMapping(value = "/logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request) {
	request.getSession().invalidate();
	return true;
    }
}
