package com.myspring.stsproject.qna_Board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	private int q_code;
	private int a_code;
	private String user_id;
	private String q_title;
	private String q_text;
	private String hos_id;
	private String hos_name;
	private String a_title;
	private String a_text;
	private String user_code;
	private String petrace;
	private String petRace;
	private String q_date;
	private String a_date;
	private int pageNum;
	private int section;
	

	private String hos_code;
	private String hos_pro;
	private String user_imgName;


	public String getUser_imgName() {
		return user_imgName;
	}

	public void setUser_imgName(String user_imgName) {
		this.user_imgName = user_imgName;
	}

	
	public String getHos_pro() {
		return hos_pro;
	}

	public void setHos_pro(String hos_pro) {
		this.hos_pro = hos_pro;
	}

	public String getHos_code() {
		return hos_code;
	}

	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}

	public ArticleVO() {
		System.out.println("ArticleVO 생성");
	}

	public int getQ_code() {
		return q_code;
	}

	public void setQ_code(int q_code) {
		this.q_code = q_code;
	}

	public int getA_code() {
		return a_code;
	}

	public void setA_code(int a_code) {
		this.a_code = a_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_text() {
		return q_text;
	}

	public void setQ_text(String q_text) {
		this.q_text = q_text;
	}

	public String getHos_name() {
		return hos_name;
	}

	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}

	public String getA_title() {
		return a_title;
	}

	public void setA_title(String a_title) {
		this.a_title = a_title;
	}

	public String getA_text() {
		return a_text;
	}

	public void setA_text(String a_text) {
		this.a_text = a_text;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getPetrace() {
		return petrace;
	}

	public void setPetrace(String petrace) {
		this.petrace = petrace;
	}


	public String getQ_date() {
		return q_date;
	}

	public void setQ_date(String q_date) {
		this.q_date = q_date;
	}

	public String getA_date() {
		return a_date;
	}

	public void setA_date(String a_date) {
		this.a_date = a_date;
	}

	public String getHos_id() {
		return hos_id;
	}

	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getPetRace() {
		return petRace;
	}

	public void setPetRace(String petRace) {
		this.petRace = petRace;
	}
	
	
}
