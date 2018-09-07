package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.pojo.Code;
import com.java.pojo.Config;
import com.java.service.CodeService;
import com.java.service.ConfigService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

@Controller
@RequestMapping("/code")
public class CodeController {
	@Autowired
	private CodeService codeService;
	@Autowired
	private ConfigService conficService;
	

	// 查看所有服务项目
	@RequestMapping("/selectItem")
	@ResponseBody
	public ResponseResult selectItem() {
		try {
			return ResponseResult.ok(true, DefaultErrMsg.SUCCESS, codeService.selectItem());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}
	 
		@RequestMapping("/getOne")
		@ResponseBody
		public ResponseResult getOne(int id) {
			try {
				return ResponseResult.ok(true, DefaultErrMsg.SUCCESS, codeService.selectByPrimaryKey(id));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}
	// 新增服务项目
	@RequestMapping("/insertItem")
	@ResponseBody
	public ResponseResult insertItem(Code code) {
		try {
			Code code1 = codeService.selectByName(code.getName());
			if(code1!=null){
				return  ResponseResult.ok(false, DefaultErrMsg.HAVE_CODE);
			}
			codeService.insertSelective(code);
			return ResponseResult.ok(true, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);

		}
	}
	// 修改服务项目
	@RequestMapping("/updateItem")
	@ResponseBody
	public ResponseResult updateItem(Code code) {
		try {
			codeService.updateByPrimaryKeySelective(code);
			return ResponseResult.ok(true, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);

		}
	}
	// 删除服务项目
		@RequestMapping("/deleteItem")
		@ResponseBody
		public ResponseResult deleteItem(Code code) {
			try {
				codeService.deleteByPrimaryKey(code);
				return ResponseResult.ok(true, DefaultErrMsg.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);

			}
		}
		//获取会员折扣
		@RequestMapping("/getDiscount")
		@ResponseBody
		public ResponseResult getDiscount() {
			try {
				Double  discount  = Double.parseDouble(conficService.selectConfigValueByName("discount"));
				return ResponseResult.ok(true, DefaultErrMsg.SUCCESS,discount);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}

		
		// 修改会员折扣
		@RequestMapping("/updateDiscount")
		@ResponseBody
		public ResponseResult updateDiscount(double discount) {
			try {
				Config config = new Config();
				config.setName("discount");
				config.setValue(String.valueOf(discount));
				conficService.updateValueByName(config);
				return ResponseResult.ok(true, DefaultErrMsg.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseResult.ok(false, DefaultErrMsg.FAILD);
			}
		}
}
