package com.sqq.util;
/**
 * 自定义相应数据结构
 * 这个类是提供给门户的用的
 * 返回状态码： 
 * 			200：成功
 * 			500：错误，错误信息在msg字段中
 * 			501：bean验证错误
 * 			502：拦截器拦截到用户token出错
 * 			555：异常抛出信息
 * @author shiqiangqiang
 */
public class BackJsonResult {
	private Integer status;	// 状态码  
	private String msg;	// 消息
	private Object data; 	// 返回数据
	
	public BackJsonResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public BackJsonResult(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public BackJsonResult(Object data) {
		this.status = 200;
		this.msg = "success";
		this.data = data;
	}
	
	/**
	 * Description: 返回给门户成功状态，并将数据返回给门户
	 * @param data
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月14日
	 */
	public static BackJsonResult ok(Object data){
		return new BackJsonResult(data);
	}
	
	public static BackJsonResult ok(String msg, Object data){
		return new BackJsonResult(200, msg, data);
	}
	
	/**
	 * Description: 返回给门户成功状态
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月14日
	 */
	public static BackJsonResult ok(String msg){
		return new BackJsonResult(200, msg);
	}
	
	/**
	 * Description: 返回处理失败，并将错误提示信息返回
	 * @param msg
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月14日
	 */
	public static BackJsonResult fail(String msg){
		return new BackJsonResult(500, msg);
	}
	
	/**
	 * Description: 返回异常
	 * @param msg
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月19日
	 */
	public static BackJsonResult errorException(String msg){
		return new BackJsonResult(555, msg, null);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
