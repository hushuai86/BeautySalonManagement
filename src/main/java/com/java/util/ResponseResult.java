package com.java.util;





import java.io.Serializable;

/**
 * 用于json返回
 * @author 
 *
 */
public class ResponseResult  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//返回状态
	private boolean success;
	//请求返回状态码
	private String code;
	//错误提示
	private String msg;
	//返回的数据
	private Object data;
	
	public ResponseResult(){
		
	}
	
	public ResponseResult(boolean success, String [] strarray, Object data) {
		this.success = success;
		this.code = strarray[0];
		this.msg = strarray[1];
		this.data = data;
	}
	
	
	public ResponseResult(boolean success, String [] strarray) {
		super();
		this.success = success;
		this.code = strarray[0];
		this.msg = strarray[1];
	}

	public static ResponseResult ok(boolean success, String [] strarray){
		return new ResponseResult(success, strarray);
	}
	
	public static ResponseResult ok(boolean success, String [] strarray, Object data){
		return new ResponseResult(success, strarray, data);
	}
	
	public static ResponseResult ok(){
		return new ResponseResult(true, DefaultErrMsg.SUCCESS, null);
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	
	
}
