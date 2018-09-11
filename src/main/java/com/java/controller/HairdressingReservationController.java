package com.java.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.pojo.Code;
import com.java.pojo.FreeTime;
import com.java.pojo.HairdresserInfo;
import com.java.pojo.HairdressingReservation;
import com.java.pojo.Account;
import com.java.pojo.UserInfo;
import com.java.service.CodeService;
import com.java.service.ConfigService;
import com.java.service.FreeTimeService;
import com.java.service.HairdresserInfoService;
import com.java.service.HairdressingReservationService;
import com.java.service.AccountService;
import com.java.service.UserInfoService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/hairdressingReservation")
public class HairdressingReservationController {
	@Autowired
	private HairdressingReservationService hairdressingReservationService;
	@Autowired
	private HairdresserInfoService hairdresserInfoService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private FreeTimeService freeTimeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ConfigService configService;

	/**
	 * @author
	 * @param hairdressingReservation
	 *            根据条件搜索订单信息
	 */
	// 按人员查询预约美容美发列表
	@RequestMapping("/userReservation")
	@ResponseBody
	public ResponseResult userReservation(HairdressingReservation hairdressingReservation, Integer accountFlag,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != accountFlag && accountFlag == 1) {// 系统管理员 只看到完成和删除的 4，5
				// 4已完成订单 5已删除订单
				String codeName = request.getParameter("codeName");
				map.put("accountFlag", accountFlag);
				if (!"".equals(codeName) && codeName != null) {
					map.put("codeName", codeName);
				}
				if (hairdressingReservation.getAppointmentStatus() != null) {
					map.put("appointmentStatus", String.valueOf(hairdressingReservation.getAppointmentStatus()));
				}
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			}
			else if (null != accountFlag && accountFlag == 2) {// 会员显示我的预约订单、管理员预约管理
				// 0:会员取消预约1:会员确定预约2:管理员接受预约3:管理员拒绝预约,8正在服务
				map.put("accountFlag", accountFlag);
				String codeName = request.getParameter("codeName");
				if (!"".equals(codeName) && codeName != null) {
					map.put("codeName", codeName);
				}
				if (hairdressingReservation.getAppointmentStatus() != null) {
					map.put("appointmentStatus", String.valueOf(hairdressingReservation.getAppointmentStatus()));
				}
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			} else if (null != accountFlag && accountFlag == 3) {// 美发师订单
																	// 传入理发师code
				// 2,已接受预约， 4已完成订单,6:待支付
				HairdresserInfo hairdresserInfo = (HairdresserInfo) request.getSession()
						.getAttribute("hairdresserInfo");
				map.put("accountFlag", accountFlag);
				hairdressingReservation.setHairdresserInfoCode(hairdresserInfo.getCode());
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			} else if (null != accountFlag && accountFlag == 5) {// 管理员
				// 4已完成订单 5已删除订单6，待支付
				String codeName = request.getParameter("codeName");
				map.put("accountFlag", accountFlag);
				if (!"".equals(codeName) && codeName != null) {
					map.put("codeName", codeName);
				}
				if (hairdressingReservation.getAppointmentStatus() != null) {
					map.put("appointmentStatus", String.valueOf(hairdressingReservation.getAppointmentStatus()));
				}
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			} else if (null != accountFlag && accountFlag == 6) {// 理发师查询
				map.put("accountFlag", accountFlag);
				HairdresserInfo hairdresserInfo = (HairdresserInfo) request.getSession()
						.getAttribute("hairdresserInfo");
				hairdressingReservation.setHairdresserInfoCode(hairdresserInfo.getCode());
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			} else if (null != accountFlag && accountFlag == 7) {// 会员 我的订单
				// 4已完成订单 6待支付订单
				String codeName = request.getParameter("codeName");
				if (!"".equals(codeName) && codeName != null) {
					map.put("codeName", codeName);
				}
				if (hairdressingReservation.getAppointmentStatus() != null) {
					map.put("appointmentStatus", String.valueOf(hairdressingReservation.getAppointmentStatus()));
				}
				map.put("accountFlag", accountFlag);
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			} else {// 会员显示和自己相关的预约订单（所有状态都可以）、系统管理员（所有状态都可以）、订单管理（所有状态都可以）
					// 0:会员取消预约，1:会员提交预约，2:管理员接受预约，3:管理员拒绝预约，4已完成订单，5已删除订单，6待支付订单。8正在服务
				map.put("accountFlag", accountFlag);
				list = hairdressingReservationService.selectAllReservation(hairdressingReservation, map);
			}
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}

	}

    	// 新增预约
    	@RequestMapping("/addReservation")
    	@ResponseBody
    	public ResponseResult addReservation(HairdressingReservation hairdressingReservation, HttpServletRequest request,
    			Integer serviceStartTime) {
    		Double discount = Double.parseDouble(configService.selectConfigValueByName("discount"));
    		// 判断余额是否充足
    		Account account = (Account) request.getSession().getAttribute("account");
    		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
    		Code code = codeService.selectByPrimaryKey(hairdressingReservation.getHairdressingProject());
    		Double dPrice = Math.ceil(code.getPrice() * discount / 10);// 会员价 向上取整
    		Integer price = dPrice.intValue();
    		// 获取用户正在预约和预约成功的订单总金额
    		Integer allPrice = hairdressingReservationService.selectSunPriceByLoginId(account.getLoginId());
    		if (user.getBalance() - allPrice < price) {
    			return ResponseResult.ok(false, DefaultErrMsg.BALANCE_NOT_ENOUGH_FOR_APPOINTMENT);
    		}
    		try { // 获取系统时间并加一天 时间，设置时间为选择的预约时间
    			Calendar cal = Calendar.getInstance();
    			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
    			cal.set(Calendar.HOUR_OF_DAY, serviceStartTime);
    			cal.set(Calendar.MINUTE, 0);
    			cal.set(Calendar.SECOND, 0);
    			cal.set(Calendar.MILLISECOND, 0);
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    			Date date = cal.getTime();
    			Date submitAppointmentTime = new Date();
    			hairdressingReservation.setSubmitAppointmentTime(df.format(submitAppointmentTime).toString());// 预约提交时间
    			hairdressingReservation.setAppointmentTime(df.format(date).toString());// 预约服务时间
    			hairdressingReservation.setPrice(price);
    			hairdressingReservation.setServiceTime(code.getTime());// 服务耗时
    			hairdressingReservation.setType("online");
    			// 将订单添加入数据库
    			hairdressingReservationService.addeServation(hairdressingReservation);
    
    			// 修改理发师空闲状态
    			FreeTime freeTime = new FreeTime();
    			freeTime.setHairdresserCode(hairdressingReservation.getHairdresserInfoCode());
    			freeTime.setTime(serviceStartTime);
    			freeTime.setStatus(1);
    			freeTimeService.updateFreeTime(freeTime);
    
    			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
    
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
    		}
    
    	}

	// 新增线下订单
	@RequestMapping("/addOne")
	@ResponseBody
	public ResponseResult addOne(HairdressingReservation hairdressingReservation, Integer userType) {
		try {
			Code code = codeService.selectByPrimaryKey(hairdressingReservation.getHairdressingProject());
			if (userType == 1) {// 线下客户
				hairdressingReservation.setUserReservation("线下客户");
				hairdressingReservation.setPrice(code.getPrice());
			} else {// 会员
				Map<String, Object> map = new HashMap<>();//判断会员是否存在
				map.put("loginId", hairdressingReservation.getUserReservation());
				Account account  = accountService.selectAccount(map);
				if (account == null) {
					return ResponseResult.ok(false, DefaultErrMsg.USER_NOT_EXISTS);
				}else if(userInfoService.selectByAccountId(account.getId())==null){
					return ResponseResult.ok(false, DefaultErrMsg.USER_NOT_EXISTS);
				}
				Double discount = Double.parseDouble(configService.selectConfigValueByName("discount"));
				Double dPrice = Math.ceil(code.getPrice() * discount / 10);// 会员价
																			// 向上取整
				hairdressingReservation.setPrice(dPrice.intValue());
			}
			hairdressingReservation.setServiceTime(code.getTime());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			hairdressingReservation.setSubmitAppointmentTime(df.format(date));
			hairdressingReservation.setAcceptAppointmentTime(df.format(date));
			hairdressingReservation.setAppointmentTime(df.format(date));
			// 订单存入数据库
			hairdressingReservationService.addeServation(hairdressingReservation);

			// 修改美发师状态 0：空闲 1：忙碌
			HairdresserInfo hairdresserInfo = hairdresserInfoService
					.selectByCode(hairdressingReservation.getHairdresserInfoCode());
			hairdresserInfo.setHairdresserStatus(1);
			hairdresserInfoService.updateByPrimaryKeySelective(hairdresserInfo);

			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}

	}

	// 修改订单状态
	@RequestMapping("/updateReservation")
	@ResponseBody
	public ResponseResult updateReservation(HairdressingReservation hairdressingReservation,
			HttpServletRequest request) {
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
			if (user == null) {// 管理员或系统管理员修改订单
				if (hairdressingReservation.getAppointmentStatus() != null
						&& hairdressingReservation.getAppointmentStatus() != 5) {// 5为删除状态
					if (hairdressingReservation.getUserReservation() != null
							&& !"线下客户".equals(hairdressingReservation.getUserReservation())) {
						Map<String, Object> map = new HashMap<>();
						map.put("loginId", hairdressingReservation.getUserReservation());
						Integer accountId = accountService.selectAccount(map).getId();
						user = userInfoService.selectByAccountId(accountId);
					}
				}
			}

			// 取消预约，如果是预约成功后取消，扣除违约金
			if (hairdressingReservation.getAppointmentStatus() != null
					&& hairdressingReservation.getAppointmentStatus() == 0) {
				Integer penalty = Integer.parseInt(request.getParameter("penalty"));
				if (penalty != null) {
					user.setBalance(user.getBalance() - penalty);
					userInfoService.updateByPrimaryKeySelective(user);
				}
				// 获取服务时间的 小时
				Integer serviceStartTime = Integer
						.parseInt(hairdressingReservation.getAppointmentTime().split(" ")[1].split(":")[0]);
				// 修改理发师状态
				FreeTime freeTime = new FreeTime();
	    			freeTime.setHairdresserCode(hairdressingReservation.getHairdresserInfoCode());
	    			freeTime.setTime(serviceStartTime);
	    			freeTime.setStatus(0);
	    			freeTimeService.updateFreeTime(freeTime);
	    
			}
			// 接受预约 //存储接受预约时间
			if (hairdressingReservation.getAppointmentStatus() != null
					&& hairdressingReservation.getAppointmentStatus() == 2) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				hairdressingReservation.setAcceptAppointmentTime(df.format(new Date()));
			}
			// 拒绝预约 //修改理发师状态
			if (hairdressingReservation.getAppointmentStatus() != null
					&& hairdressingReservation.getAppointmentStatus() == 3) {
				// 获取服务时间的 小时
				Integer serviceStartTime = Integer
						.parseInt(hairdressingReservation.getAppointmentTime().split(" ")[1].split(":")[0]);
				// 修改理发师状态
				FreeTime freeTime = new FreeTime();
	    			freeTime.setHairdresserCode(hairdressingReservation.getHairdresserInfoCode());
	    			freeTime.setTime(serviceStartTime);
	    			freeTime.setStatus(0);
	    			freeTimeService.updateFreeTime(freeTime);
	    
			}
			// 完成订单，扣除费用
			if (hairdressingReservation.getAppointmentStatus() != null
					&& hairdressingReservation.getAppointmentStatus() == 4) {
				HairdressingReservation hr = hairdressingReservationService
						.selectByPrimaryKey(hairdressingReservation.getId());
				if ("offline".equals(hr.getType()) && "balance".equals(hairdressingReservation.getPayType())) {// 余额支付
																												// 判断余额是否足够
					Integer price = hr.getPrice();
					// 获取用户正在预约和预约成功的订单总金额
					Integer allPrice = hairdressingReservationService.selectSunPriceByLoginId(hr.getUserReservation());
					if (user.getBalance() - allPrice < price) {
						return ResponseResult.ok(false, DefaultErrMsg.BALANCE_NOT_ENOUGH_FOR_COMPLETE);
					}
				}
				if ("balance".equals(hairdressingReservation.getPayType())) {
					Integer price = hr.getPrice();
					if (user != null) {
						user.setBalance(user.getBalance() - price);
						userInfoService.updateByPrimaryKeySelective(user);
					}
				}
				
				// 修改美发师状态 0：空闲 1：忙碌
				HairdresserInfo hairdresserInfo = hairdresserInfoService.selectByCode(hr.getHairdresserInfoCode());
				hairdresserInfo.setHairdresserStatus(0);
				hairdresserInfoService.updateByPrimaryKeySelective(hairdresserInfo);
			}
			if (hairdressingReservation.getAppointmentStatus() != null
					&& hairdressingReservation.getAppointmentStatus() == 7) {// 系统管理员彻底删除数据
				hairdressingReservationService.deleteByPrimaryKey(hairdressingReservation.getId());
			}
			if (hairdressingReservation.getAppointmentStatus() != null
				&& hairdressingReservation.getAppointmentStatus() == 8) {// 开始服务
			    HairdressingReservation hr = hairdressingReservationService
					.selectByPrimaryKey(hairdressingReservation.getId());
			    //将美发师当前状态改为忙碌
			    HairdresserInfo hairdresserInfo = hairdresserInfoService.selectByCode(hr.getHairdresserInfoCode());
			    hairdresserInfo.setHairdresserStatus(1);
			    hairdresserInfoService.updateByPrimaryKeySelective(hairdresserInfo);
		}
			hairdressingReservationService.updateByPrimaryKeySelective(hairdressingReservation);
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}

	}


	@RequestMapping("/getOne")
	@ResponseBody
	public ResponseResult getOne(int id) {
		try {
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS,
					hairdressingReservationService.selectByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}

	}

	/**
	 * @author
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param excel
	 *            为1的时候是导出excel表格
	 */
	@SuppressWarnings("unchecked")
	// 统计报表
	@RequestMapping("/statisticalReport")
	@ResponseBody
	public ResponseResult statisticalReport(

			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "excel", required = false) Integer excel, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Account account = new Account();
			HairdresserInfo hairdresserInfo = new HairdresserInfo();
			account.setType(2);
			account.setStatus(1);
			// 查看所有已激活理发师帐号列表
			List<Account> accountList = accountService.selectAllAccount(account);
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			int turnover = 0;// 营业额
			int allWages = 0;// 总工资
			if (accountList != null) {
				// 计算每个理发师的工资情况
				for (Account u : accountList) {
					// 每次循环初始化ID和理发师编号
					hairdresserInfo.setAccountId(null);
					hairdresserInfo.setCode(null);
					// 根据id来获取理发师详情
					hairdresserInfo.setAccountId(u.getId());
					// 获取理发师个人信息表
					List<HairdresserInfo> list = new ArrayList<>(hairdresserInfoService.selectCharge(hairdresserInfo));
					JSONObject jsonObject2 = new JSONObject();
					JSONArray jsonArray2 = new JSONArray();
					int baseCharge = 0;// 基本工资
					int allItemPay = 0;// 所有服务项目的总工资
					int allCharge = 0;// 理发师总工资
					double commission = 0;// 提成率
					// 获取基本工资和提成率
					if (list != null && !list.isEmpty() && list.size() > 0) {
						for (HairdresserInfo info : list) {
							baseCharge = info.getBaseCharge();
							commission = info.getCommission();
							// 设置理发师编号，根据它来查找理发师订单
							hairdresserInfo.setCode(info.getCode());
							jsonObject.put("hairdresserName", info.getName());
							jsonObject.put("hairdresserCode", info.getCode());
							jsonObject.put("baseCharge", info.getBaseCharge());
							jsonObject.put("commission", info.getCommission());
							jsonObject.put("accountId", info.getAccountId());
							// 设置时间格式
							Map<String, Object> map = new HashMap<>();
							map.put("hairdresserInfoCode", hairdresserInfo.getCode());// 要查的理发师编号
							map.put("appointmentStatus", 4);// 要查的订单状态4代表管理员完成预约
							map.put("startDate", startDate);// 大于等于此订单的日期
							map.put("endDate", endDate);// 小于等于此订单的日期
							List<Map<String, Object>> listRes = new ArrayList<>(
									hairdressingReservationService.selectAllCondition(map));
							// 获取所有服务项目
							List<Code> codeList = new ArrayList<>(codeService.selectItem());
							// 获取每个服务项目工资之和
							if (codeList != null) {
								if (listRes != null) {
									for (Code codeL : codeList) {
										int oneItemPay = 0;// 单个条目总工资
										for (Map<String, Object> mapRes : listRes) {
											if (codeL.getId().equals(mapRes.get("hairdressing_project"))) {
												oneItemPay = oneItemPay + (int) mapRes.get("price");// 单个条目总工资
											}
										}
										jsonObject2.put("name", codeL.getName());
										System.out.println("单个服务项目的总费用为" + oneItemPay);
										jsonObject2.put("value", oneItemPay);
										jsonArray2.add(jsonObject2);
										allItemPay = allItemPay + oneItemPay;
									}
									System.out.println("所有服务项目的总费用为" + allItemPay);
									jsonObject.put("allItemPay", allItemPay);
								}
							}
							allCharge = (int) (baseCharge + allItemPay * commission);
							// jsonObject.put("chargeList", listRes);
							jsonObject.put("allCharge", allCharge);
							jsonObject.put("itemChargeAll", jsonArray2);
							jsonArray.add(jsonObject);
							turnover = turnover + baseCharge + allItemPay;
							allWages = allWages + allCharge;
						}
					}

				}
			}
			// 导出excel表
			if (excel != null && excel.equals(1)) {
				exportExcel(jsonArray, response, request);
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", jsonArray);
			map.put("turnover", turnover);
			map.put("allWages", allWages);
			map.put("profit", turnover - allWages);
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}

	// 生成统计报表
	public void exportExcel(List<Map<String, Object>> list, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("统计报表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("美发店业绩统计一览表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("理发师昵称");
		row2.createCell(1).setCellValue("理发师编号");
		row2.createCell(2).setCellValue("基本工资(元)");
		row2.createCell(3).setCellValue("提成");
		row2.createCell(4).setCellValue("各美发项目总收入(元)");
		row2.createCell(5).setCellValue("实际总工资(元)");
		// 在sheet里创建第三行
		if (list != null) {
			int n = 2;
			for (Map<String, Object> map : list) {
				HSSFRow row = sheet.createRow(n);
				row.createCell(0).setCellValue(map.get("hairdresserName").toString());
				row.createCell(1).setCellValue(map.get("hairdresserCode").toString());
				row.createCell(2).setCellValue((int) map.get("baseCharge"));
				row.createCell(3).setCellValue((double) map.get("commission"));
				row.createCell(4).setCellValue((int) map.get("allItemPay"));
				row.createCell(5).setCellValue((int) map.get("allCharge"));
				n++;
			}
		}
		// 输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=details.xls");
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();

	}

}
