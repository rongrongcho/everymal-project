package com.myspring.stsproject.member.vo;


import java.sql.Date;

import org.springframework.stereotype.Component;


@Component("memberVO")
public class MemberVO { 
	private String hos_name;
	private String code;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date j_date;
	private boolean hos_chk;
	private String hos_dno;
	private Date joinDate;
	
	public MemberVO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJ_date() {
		return j_date;
	}

	public void setJ_date(Date j_date) {
		this.j_date = j_date;
	}

	public boolean getHos_chk() {
		return hos_chk;
	}

	public void setHos_chk(boolean hos_chk) {
		this.hos_chk = hos_chk;
	}

	public String getHos_name() {
		return hos_name;
	}

	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHos_dno() {
		return hos_dno;
	}

	public void setHos_dno(String hos_dno) {
		this.hos_dno = hos_dno;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
