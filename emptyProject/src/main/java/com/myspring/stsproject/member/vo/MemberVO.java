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
	
	private String user_code;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_tel;
	private String user_email;
	private String user_addr1;
	private String user_addr2;
	private String user_zipcode;
	private String user_imgname;
	
	private String hos_code;
	   private String hos_pro;
	   private String hos_id;
	   private String hos_username;
	   private String hos_usertel;
	   private String hos_pwd;
	   private String hos_tel;
	   private String hos_zipcode;
	   private String hos_addr1;
	   private String hos_addr2;
	   private String hos_email;
	   private String hos_intro;
	   private String hos_mailing;
	   private String hos_24;
	   private String hos_365;
	   private String hos_time_s;
	   private String hos_time_e;
	   private String hos_dog;
	   private String hos_cat;
	   private String hos_small;
	   private String hos_fish;
	   private String hos_bird;
	   private String hos_rep;
	   private String hos_etc;
	   private String hos_gs;
	   private String hos_im;
	   private String hos_em;
	   private String hos_rm;
	   private String hos_vac;
	   private String tel_front;
	   private String tel_end;
	   
	   private String rv_text;
	   private int rv_rate;
	   private String rv_code;
	   
	   private String rv_title;
	   private String res_code;
	
	public MemberVO() {
		
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_addr1() {
		return user_addr1;
	}

	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}

	public String getUser_addr2() {
		return user_addr2;
	}

	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}

	public String getUser_zipcode() {
		return user_zipcode;
	}

	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}

	public String getUser_imgname() {
		return user_imgname;
	}

	public void setUser_imgname(String user_imgname) {
		this.user_imgname = user_imgname;
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

	public String getHos_code() {
		return hos_code;
	}

	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}

	public String getHos_pro() {
		return hos_pro;
	}

	public void setHos_pro(String hos_pro) {
		this.hos_pro = hos_pro;
	}

	public String getHos_id() {
		return hos_id;
	}

	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
	}

	public String getHos_username() {
		return hos_username;
	}

	public void setHos_username(String hos_username) {
		this.hos_username = hos_username;
	}

	public String getHos_usertel() {
		return hos_usertel;
	}

	public void setHos_usertel(String hos_usertel) {
		this.hos_usertel = hos_usertel;
	}

	public String getHos_pwd() {
		return hos_pwd;
	}

	public void setHos_pwd(String hos_pwd) {
		this.hos_pwd = hos_pwd;
	}

	public String getHos_tel() {
		return hos_tel;
	}

	public void setHos_tel(String hos_tel) {
		this.hos_tel = hos_tel;
	}

	public String getHos_zipcode() {
		return hos_zipcode;
	}

	public void setHos_zipcode(String hos_zipcode) {
		this.hos_zipcode = hos_zipcode;
	}

	public String getHos_addr1() {
		return hos_addr1;
	}

	public void setHos_addr1(String hos_addr1) {
		this.hos_addr1 = hos_addr1;
	}

	public String getHos_addr2() {
		return hos_addr2;
	}

	public void setHos_addr2(String hos_addr2) {
		this.hos_addr2 = hos_addr2;
	}

	public String getHos_email() {
		return hos_email;
	}

	public void setHos_email(String hos_email) {
		this.hos_email = hos_email;
	}

	public String getHos_intro() {
		return hos_intro;
	}

	public void setHos_intro(String hos_intro) {
		this.hos_intro = hos_intro;
	}

	public String getHos_mailing() {
		return hos_mailing;
	}

	public void setHos_mailing(String hos_mailing) {
		this.hos_mailing = hos_mailing;
	}

	public String getHos_24() {
		return hos_24;
	}

	public void setHos_24(String hos_24) {
		this.hos_24 = hos_24;
	}

	public String getHos_365() {
		return hos_365;
	}

	public void setHos_365(String hos_365) {
		this.hos_365 = hos_365;
	}

	public String getHos_time_s() {
		return hos_time_s;
	}

	public void setHos_time_s(String hos_time_s) {
		this.hos_time_s = hos_time_s;
	}

	public String getHos_time_e() {
		return hos_time_e;
	}

	public void setHos_time_e(String hos_time_e) {
		this.hos_time_e = hos_time_e;
	}

	public String getHos_dog() {
		return hos_dog;
	}

	public void setHos_dog(String hos_dog) {
		this.hos_dog = hos_dog;
	}

	public String getHos_cat() {
		return hos_cat;
	}

	public void setHos_cat(String hos_cat) {
		this.hos_cat = hos_cat;
	}

	public String getHos_small() {
		return hos_small;
	}

	public void setHos_small(String hos_small) {
		this.hos_small = hos_small;
	}

	public String getHos_fish() {
		return hos_fish;
	}

	public void setHos_fish(String hos_fish) {
		this.hos_fish = hos_fish;
	}

	public String getHos_bird() {
		return hos_bird;
	}

	public void setHos_bird(String hos_bird) {
		this.hos_bird = hos_bird;
	}

	public String getHos_rep() {
		return hos_rep;
	}

	public void setHos_rep(String hos_rep) {
		this.hos_rep = hos_rep;
	}

	public String getHos_etc() {
		return hos_etc;
	}

	public void setHos_etc(String hos_etc) {
		this.hos_etc = hos_etc;
	}

	public String getHos_gs() {
		return hos_gs;
	}

	public void setHos_gs(String hos_gs) {
		this.hos_gs = hos_gs;
	}

	public String getHos_im() {
		return hos_im;
	}

	public void setHos_im(String hos_im) {
		this.hos_im = hos_im;
	}

	public String getHos_em() {
		return hos_em;
	}

	public void setHos_em(String hos_em) {
		this.hos_em = hos_em;
	}

	public String getHos_rm() {
		return hos_rm;
	}

	public void setHos_rm(String hos_rm) {
		this.hos_rm = hos_rm;
	}

	public String getHos_vac() {
		return hos_vac;
	}

	public void setHos_vac(String hos_vac) {
		this.hos_vac = hos_vac;
	}

	public String getTel_front() {
		return tel_front;
	}

	public void setTel_front(String tel_front) {
		this.tel_front = tel_front;
	}

	public String getTel_end() {
		return tel_end;
	}

	public void setTel_end(String tel_end) {
		this.tel_end = tel_end;
	}

	public String getRv_text() {
		return rv_text;
	}

	public void setRv_text(String rv_text) {
		this.rv_text = rv_text;
	}

	public int getRv_rate() {
		return rv_rate;
	}

	public void setRv_rate(int rv_rate) {
		this.rv_rate = rv_rate;
	}

	public String getRv_code() {
		return rv_code;
	}

	public void setRv_code(String rv_code) {
		this.rv_code = rv_code;
	}

	public String getRv_title() {
		return rv_title;
	}

	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	
	

	
	
}
