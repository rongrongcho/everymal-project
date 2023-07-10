package com.myspring.stsproject.userMypage.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;
@Component("userVO")
public class UserVO {
	
	//userMypage 변수 선언
	private String user_code;
	private String user_id; 
	private String user_pwd; // user_
	private String user_name; // user_name
	private String user_zipcode; // 우편번호 
	private String user_addr1; // user_addr // 도로명 주소 
	private String user_addr2; // user_addr // 상세 주소2
	private String user_imgName; // 테이블 user_Img
	private String user_email; //user_email
	private String user_tel; // user_tel
	private Date j_date; // J_date;

	
	
	//myPetInfo 변수 선언 
	
	private String pet_name;
	private int pet_age;
	private String pet_sex;
	private String pet_number;
	private String b_type;
	private float pet_weight;
	private String pet_etc;
	private String pet_types;
	
	
	//myReview 변수 선언 
	
	
	private String rv_code; // 리뷰 코드 
	private String rv_title; // 리뷰 제목
	private String rv_text; // 리뷰 내용
	private int rv_rate; // 리뷰 별점
	private Date rv_date; // 리뷰 작성일
	private String rv_imgname; // 리뷰 첨부파일
	
	
	
	//
	
	private String res_code;
	private String[] items;
	
	
	

	
	//myQuestion변수 선언
	
	private String q_code; // 질문 게시글 코드
	private Date q_date;
	private String q_title;
	private String q_text;
	//=====
	private String a_code; // 답변 코드 
	private Date a_date;
	private String petrace;
	private String a_title;
	private String a_text;
	
	private String isAnswer; // 답변 등록 여부 



		

//=============================================================================
	
	
	
	


	public UserVO() {
		super();
	}





	public UserVO(String user_code, String user_id, String user_pwd, String user_name, String user_zipcode,
			String user_addr1, String user_addr2, String user_imgName, String user_email, String user_tel, Date j_date,
			String pet_name, int pet_age, String pet_sex, String pet_number, String b_type, float pet_weight,
			String pet_etc, String pet_types, String rv_code, String rv_title, String rv_text, int rv_rate,
			Date rv_date, String rv_imgname, String res_code, String[] items, String q_code, Date q_date,
			String q_title, String q_text, String a_code, Date a_date, String petrace, String a_title, String a_text,
			String isAnswer) {
		super();
		this.user_code = user_code;
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_zipcode = user_zipcode;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.user_imgName = user_imgName;
		this.user_email = user_email;
		this.user_tel = user_tel;
		this.j_date = j_date;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_etc = pet_etc;
		this.pet_types = pet_types;
		this.rv_code = rv_code;
		this.rv_title = rv_title;
		this.rv_text = rv_text;
		this.rv_rate = rv_rate;
		this.rv_date = rv_date;
		this.rv_imgname = rv_imgname;
		this.res_code = res_code;
		this.items = items;
		this.q_code = q_code;
		this.q_date = q_date;
		this.q_title = q_title;
		this.q_text = q_text;
		this.a_code = a_code;
		this.a_date = a_date;
		this.petrace = petrace;
		this.a_title = a_title;
		this.a_text = a_text;
		this.isAnswer = isAnswer;
	}





	public String getUser_code() {
		return user_code;
	}





	public String getUser_id() {
		return user_id;
	}





	public String getUser_pwd() {
		return user_pwd;
	}





	public String getUser_name() {
		return user_name;
	}





	public String getUser_zipcode() {
		return user_zipcode;
	}





	public String getUser_addr1() {
		return user_addr1;
	}





	public String getUser_addr2() {
		return user_addr2;
	}





	public String getUser_imgName() {
		return user_imgName;
	}





	public String getUser_email() {
		return user_email;
	}





	public String getUser_tel() {
		return user_tel;
	}





	public Date getJ_date() {
		return j_date;
	}





	public String getPet_name() {
		return pet_name;
	}





	public int getPet_age() {
		return pet_age;
	}





	public String getPet_sex() {
		return pet_sex;
	}





	public String getPet_number() {
		return pet_number;
	}





	public String getB_type() {
		return b_type;
	}





	public float getPet_weight() {
		return pet_weight;
	}





	public String getPet_etc() {
		return pet_etc;
	}





	public String getPet_types() {
		return pet_types;
	}





	public String getRv_code() {
		return rv_code;
	}





	public String getRv_title() {
		return rv_title;
	}





	public String getRv_text() {
		return rv_text;
	}





	public int getRv_rate() {
		return rv_rate;
	}





	public Date getRv_date() {
		return rv_date;
	}





	public String getRv_imgname() {
		return rv_imgname;
	}





	public String getRes_code() {
		return res_code;
	}





	public String[] getItems() {
		return items;
	}





	public String getQ_code() {
		return q_code;
	}





	public Date getQ_date() {
		return q_date;
	}





	public String getQ_title() {
		return q_title;
	}





	public String getQ_text() {
		return q_text;
	}





	public String getA_code() {
		return a_code;
	}





	public Date getA_date() {
		return a_date;
	}





	public String getPetrace() {
		return petrace;
	}





	public String getA_title() {
		return a_title;
	}





	public String getA_text() {
		return a_text;
	}





	public String getIsAnswer() {
		return isAnswer;
	}





	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}





	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}





	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}





	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}





	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}





	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}





	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}





	public void setUser_imgName(String user_imgName) {
		this.user_imgName = user_imgName;
	}





	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}





	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}





	public void setJ_date(Date j_date) {
		this.j_date = j_date;
	}





	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}





	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}





	public void setPet_sex(String pet_sex) {
		this.pet_sex = pet_sex;
	}





	public void setPet_number(String pet_number) {
		this.pet_number = pet_number;
	}





	public void setB_type(String b_type) {
		this.b_type = b_type;
	}





	public void setPet_weight(float pet_weight) {
		this.pet_weight = pet_weight;
	}





	public void setPet_etc(String pet_etc) {
		this.pet_etc = pet_etc;
	}





	public void setPet_types(String pet_types) {
		this.pet_types = pet_types;
	}





	public void setRv_code(String rv_code) {
		this.rv_code = rv_code;
	}





	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}





	public void setRv_text(String rv_text) {
		this.rv_text = rv_text;
	}





	public void setRv_rate(int rv_rate) {
		this.rv_rate = rv_rate;
	}





	public void setRv_date(Date rv_date) {
		this.rv_date = rv_date;
	}





	public void setRv_imgname(String rv_imgname) {
		this.rv_imgname = rv_imgname;
	}





	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}





	public void setItems(String[] items) {
		this.items = items;
	}





	public void setQ_code(String q_code) {
		this.q_code = q_code;
	}





	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}





	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}





	public void setQ_text(String q_text) {
		this.q_text = q_text;
	}





	public void setA_code(String a_code) {
		this.a_code = a_code;
	}





	public void setA_date(Date a_date) {
		this.a_date = a_date;
	}





	public void setPetrace(String petrace) {
		this.petrace = petrace;
	}





	public void setA_title(String a_title) {
		this.a_title = a_title;
	}





	public void setA_text(String a_text) {
		this.a_text = a_text;
	}





	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}





	



	
	
	
	
	
	
	
	}