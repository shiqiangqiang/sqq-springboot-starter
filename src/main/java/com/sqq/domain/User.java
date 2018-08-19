package com.sqq.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户类
 * @author shiqiangqiang
 * 
 */
public class User {
	private String name;
	
	@JsonIgnore   // 不想将password返回给前端
	private String password;
	
	private Integer age;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
	private Date birthday;
	
	@JsonInclude(Include.NON_NULL)	// 当describe数据为空时，不显示给前端
	private String describe;	
	
	public User(){}
	
	public User(String name, String password, Integer age, Date birthday,
			String describe) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
		this.birthday = birthday;
		this.describe = describe;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
