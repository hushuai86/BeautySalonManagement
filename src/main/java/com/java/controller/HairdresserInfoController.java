package com.java.controller;

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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.pojo.Code;
import com.java.pojo.HairdresserInfo;
import com.java.pojo.Account;
import com.java.service.CodeService;
import com.java.service.HairdresserInfoService;
import com.java.service.HairdressingReservationService;
import com.java.service.AccountService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/hairdresserInfo")
public class HairdresserInfoController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private HairdresserInfoService hairdresserInfoService;
	@Autowired
	private HairdressingReservationService hairdressingReservationService;
	@Autowired
	private CodeService codeService;
	
	//美发师获取个人信息
		@RequestMapping("/getOne")
		@ResponseBody
		public ResponseResult getOne(HttpServletRequest request) {
			try {
				HttpSession session =request.getSession();
				HairdresserInfo hairdresserInfo=(HairdresserInfo)session.getAttribute("hairdresserInfo");
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, hairdresserInfoService.selectByAccountId(hairdresserInfo.getAccountId()));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}
		//获得单个美发师信息
		@RequestMapping("/selectHairdresserInfo")
		@ResponseBody
		public ResponseResult selectHairdresserInfo(String  code) {
			try {
				if( hairdresserInfoService.selectByCode(code)==null){
					return ResponseResult.ok(false, DefaultErrMsg.HAIRDRESSER_NOT_EXIST);
				}
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, hairdresserInfoService.selectByCode(code));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}
		// 修改理发师信息
		@RequestMapping("/updateInfo2")
		@ResponseBody
		public ResponseResult updateInfo2( HairdresserInfo hairdresserInfo,
				@RequestParam(value = "file", required = false) MultipartFile file
				) {
			try {
				if(null==file){
					System.out.println("没有修改图片");
				}else{
					hairdresserInfo.setPicUrl(file.getBytes());
				}
				hairdresserInfoService.updateByPrimaryKeySelective(hairdresserInfo);
				return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}

		}
	// 查看美发师列表
	@RequestMapping("/hairdresserList")
	@ResponseBody
	public ResponseResult hairdresserList(Account account, HairdresserInfo hairdresserInfo) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> selectGoodMap = new HashMap<>();
			Map<String, Object> selectBadMap = new HashMap<>();
			JSONArray jsonObject = new JSONArray();
			// 获取理发师信息
			list = accountService.selectHairdresser(hairdresserInfo);
			// 获取评价
			for (Map<String, Object> map : list) {
				selectGoodMap.put("hairdresserInfoCode", map.get("code"));
				selectGoodMap.put("evaluate", 1);
				selectBadMap.put("hairdresserInfoCode", map.get("code"));
				selectBadMap.put("evaluate", 0);
				int good = hairdressingReservationService.selectEvaluate(selectGoodMap);
				int bad = hairdressingReservationService.selectEvaluate(selectBadMap);
				int all = good + bad;
				map.put("allEvaluate", good + "/" + all);
				map.put("good", good);
				map.put("bad", bad);
				jsonObject.add(map);
			}
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, jsonObject);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}
	//获取美发师图片
	@RequestMapping("/hairdresserPic")
	@ResponseBody
	public void photo(HttpServletRequest request, HttpServletResponse response,Integer id){
		try {
		OutputStream out = response.getOutputStream();
		out.write(hairdresserInfoService.selectPicStream(id));
		out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 查看本月当前工资
	@RequestMapping("/selectCharge")
	@ResponseBody
	public ResponseResult selectCharge(Account account, HairdresserInfo hairdresserInfo) {
		try {
			//获取理发师个人信息表
			List<HairdresserInfo> list = new ArrayList<>(hairdresserInfoService.selectCharge(hairdresserInfo));
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray=new JSONArray();
			int baseCharge = 0;// 基本工资
			int itemCharge = 0;// 条目价格
			int allCharge = 0;
			double commission = 0;// 提成率
			// 获取基本工资和提成率
			if (list != null && !list.isEmpty() && list.size() > 0) {
				for (HairdresserInfo info : list) {
					baseCharge = info.getBaseCharge();
					commission = info.getCommission();
					jsonObject.put("baseCharge", info.getBaseCharge());
					jsonObject.put("commission", info.getCommission());
				}
			}
			// 获取本月第一天日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date date = calendar.getTime();
			Map<String, Object> map = new HashMap<>();
			map.put("hairdresserInfoCode", hairdresserInfo.getCode());// 要查的员工编号
			map.put("appointmentStatus", 4);// 要查的订单状态4代表管理员完成预约
			map.put("appointmentTime", df.format(date).toString());// 大于此订单的日期（每个月1号到当前日期的订单）
			List<Map<String, Object>> listRes = new ArrayList<>(hairdressingReservationService.selectAllCondition(map));
			// 获取该员工所有条目价格总和
			if (listRes != null && !listRes.isEmpty() && listRes.size() > 0) {
				for (Map<String, Object> mapRes : listRes) {
					itemCharge = (int) (itemCharge + (int) mapRes.get("price"));// 所有条目总和
				}
			}
			// 获取所有服务项目
			List<Code> codeList = new ArrayList<>(codeService.selectItem());
			//获取每个服务项目工资之和
			if (codeList != null && !codeList.isEmpty() && codeList.size() > 0) {
				if (listRes != null && !listRes.isEmpty() && listRes.size() > 0) {
					for (Code codeL : codeList) {
						int itemPay = 0;
						for (Map<String, Object> mapRes : listRes) {
							if (codeL.getId().equals(mapRes.get("hairdressing_project"))) {
								itemPay = (int) mapRes.get("price");// 该条目总工资
							}
						}
						jsonObject2.put("name", codeL.getName());
						jsonObject2.put("value", itemPay);
						jsonArray.add(jsonObject2);
					}

				}

			}
			allCharge = (int) (baseCharge + itemCharge * commission);
			//jsonObject.put("chargeList", listRes);
			jsonObject.put("allCharge", allCharge);
			jsonObject.put("itemChargeAll", jsonArray);
			
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}

}
