package com.java.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.pojo.Pic;
import com.java.service.PicService;
import com.java.util.DefaultErrMsg;
import com.java.util.ResponseResult;

@Controller
@RequestMapping("/pic")

public class PicController {
	@Autowired
	private PicService picService;

	// 添加店铺图片
	@RequestMapping("/insertPic")
	@ResponseBody
	public ResponseResult insertPic(@RequestParam("file") MultipartFile file) {
		try {
			Pic pic = new Pic();
			pic.setName(file.getName());
			pic.setPhoto(file.getBytes());
			picService.insertSelective(pic);
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}
	// 修改店铺图片
	@RequestMapping("/updatePic")
	@ResponseBody
	public ResponseResult updatePic(@RequestParam("file") MultipartFile file,Pic pic) {
		try {
			pic.setName(file.getName());
			pic.setPhoto(file.getBytes());
			picService.updateByPrimaryKeySelective(pic);
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}
	// 删除店铺图片
	@RequestMapping("/deletePic")
	@ResponseBody
	public ResponseResult deletePic(Integer id) {
		try {
			picService.deleteByPrimaryKey(id);
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}

	// 显示店铺图片列表
	@RequestMapping("/selectPicList")
	@ResponseBody
	public ResponseResult selectPic() {
		try {
			return ResponseResult.ok(false, DefaultErrMsg.SUCCESS, picService.selectPicList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseResult.ok(false, DefaultErrMsg.FAILD);
		}
	}

	// 显示店铺图片流
	@RequestMapping("/selectPicStream")
	@ResponseBody
	public void photo(HttpServletRequest request, HttpServletResponse response,Integer id){
		try {
		OutputStream out = response.getOutputStream();
		out.write(picService.selectPicStream(id));
		out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
