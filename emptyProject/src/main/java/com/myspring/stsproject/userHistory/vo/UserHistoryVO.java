package com.myspring.stsproject.userHistory.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import java.sql.Date;

import java.sql.Date;


@Component("userHistoryVO")
public class UserHistoryVO {
	
	 //============================= 병원 예약 

	   //private String hos_pro;
	   //============================
	   private String user_id;
	   private String res_code; //  예약코드 
	   private String user_code; // 회원 코드 
	   private String hos_code; // 병원 코드 
	   private String pet_code; // 동물 코드 
	   
	   private String user_name; // 예약자 성명
	   private String user_tel; // 예약자 연락처 
	   private String useR_id; // 회원 아이디 
	      
	   private String hos_name; //병원 이름
	   private String hos_sub; //진료 과목 
	   
	   
	   
	   
	   public UserHistoryVO(String hos_sub) {
	      super();
	      this.hos_sub = hos_sub;
	   }

	   
	   
	   private Date receipt_date; // 예약 접수일
	   private Date res_date; // 예약 날짜 
	   private String res_time; // 예약 시간
	   
	   private String pet_name; // 동물 이름
	   private String pet_sex; //동물 성별
	   private int pet_age; // 동물 나이 
	   private String b_type; // 동물 혈액형 
	   private float pet_weight; // 동물 몸무게 
	   private String pet_types; //동물 종류 
	   private String pet_number;// 동물 등록번호 
	   
	   
	   
	   
	
	   
	   
	   public String getPet_number() {
	      return pet_number;
	   }



	   public void setPet_number(String pet_number) {
	      this.pet_number = pet_number;
	   }



	   private String res_etc; // 기타 사항
	   
	   
	   //---------가장 최근 예약(병원) 
	   private Date r_res_date;
	   private Date r_receipt_date;
	   private String r_hos_name;
	   private String r_res_code;
	   private String r_hos_code;
	   

	   
	   //================================= 펫 택시 예약 정보  
	   private String tx_code; // 택시 코드 
	   private String tx_res_code; // 택시 예약 코드 
	   private Date tx_reserv_day; //예약 날짜 
	   private String tx_dep; //  출발지 
	   private String tx_arr; // 목적지 
	   
	   //====================================택시 정보 테이블 
	   private String tx_number; // 택시 번호 
	   private String tx_name; // 택시 기사 이름 
	   private String tx_local; // 택시 지역 
	   private String tx_tel; // 택시 전화번호 

	   //============================택시 최근 예약
	   
	   private String ttx_code; // 택시 코드 
	   private String ttx_res_code; // 택시 예약 코드 
	   private Date ttx_reserv_day; //예약 날짜 
	   private String ttx_dep; //  출발지 
	   private String ttx_arr; // 목적지 
	   
	   
	   private String ttx_number; // 택시 번호 
	   private String ttx_name; // 택시 기사 이름 
	   private String ttx_tel; // 택시 전화번호 
	   private String tx_img; //택시 드라이버 이미지 
	   private String tx_restime;
	   private String tx_intro; 
	   
	   
	   
	   //
	   
	   
	   private String res_name; // 예약자 성명
	   
	   //hosjointbl에서 가져온 것
	   
	   private String hos_thum; // 병원 썸네일 

	   
	   
	   //===========생성자
	   

	   
	   public UserHistoryVO() {
	      super();
	   }



	public UserHistoryVO(String user_id, String res_code, String user_code, String hos_code, String pet_code, String user_name,
			String user_tel, String useR_id2, String hos_name, String hos_sub, Date receipt_date, Date res_date,
			String res_time, String pet_name, String pet_sex, int pet_age, String b_type, float pet_weight,
			String pet_types, String pet_number, String res_etc, Date r_res_date, Date r_receipt_date, String r_hos_name,
			String r_res_code, String r_hos_code, String tx_code, String tx_res_code, Date tx_reserv_day, String tx_dep,
			String tx_arr, String tx_number, String tx_name, String tx_local, String tx_tel, String ttx_code,
			String ttx_res_code, Date ttx_reserv_day, String ttx_dep, String ttx_arr, String ttx_number, String ttx_name,
			String ttx_tel, String tx_img, String tx_restime, String tx_intro, String res_name, String hos_thum) {
		super();
		this.user_id = user_id;
		this.res_code = res_code;
		this.user_code = user_code;
		this.hos_code = hos_code;
		this.pet_code = pet_code;
		this.user_name = user_name;
		this.user_tel = user_tel;
		useR_id = useR_id2;
		this.hos_name = hos_name;
		this.hos_sub = hos_sub;
		this.receipt_date = receipt_date;
		this.res_date = res_date;
		this.res_time = res_time;
		this.pet_name = pet_name;
		this.pet_sex = pet_sex;
		this.pet_age = pet_age;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.res_etc = res_etc;
		this.r_res_date = r_res_date;
		this.r_receipt_date = r_receipt_date;
		this.r_hos_name = r_hos_name;
		this.r_res_code = r_res_code;
		this.r_hos_code = r_hos_code;
		this.tx_code = tx_code;
		this.tx_res_code = tx_res_code;
		this.tx_reserv_day = tx_reserv_day;
		this.tx_dep = tx_dep;
		this.tx_arr = tx_arr;
		this.tx_number = tx_number;
		this.tx_name = tx_name;
		this.tx_local = tx_local;
		this.tx_tel = tx_tel;
		this.ttx_code = ttx_code;
		this.ttx_res_code = ttx_res_code;
		this.ttx_reserv_day = ttx_reserv_day;
		this.ttx_dep = ttx_dep;
		this.ttx_arr = ttx_arr;
		this.ttx_number = ttx_number;
		this.ttx_name = ttx_name;
		this.ttx_tel = ttx_tel;
		this.tx_img = tx_img;
		this.tx_restime = tx_restime;
		this.tx_intro = tx_intro;
		this.res_name = res_name;
		this.hos_thum = hos_thum;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getRes_code() {
		return res_code;
	}



	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}



	public String getUser_code() {
		return user_code;
	}



	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}



	public String getHos_code() {
		return hos_code;
	}



	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}



	public String getPet_code() {
		return pet_code;
	}



	public void setPet_code(String pet_code) {
		this.pet_code = pet_code;
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



	public String getUseR_id() {
		return useR_id;
	}



	public void setUseR_id(String useR_id) {
		this.useR_id = useR_id;
	}



	public String getHos_name() {
		return hos_name;
	}



	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}



	public String getHos_sub() {
		return hos_sub;
	}



	public void setHos_sub(String hos_sub) {
		this.hos_sub = hos_sub;
	}



	public Date getReceipt_date() {
		return receipt_date;
	}



	public void setReceipt_date(Date receipt_date) {
		this.receipt_date = receipt_date;
	}



	public Date getRes_date() {
		return res_date;
	}



	public void setRes_date(Date res_date) {
		this.res_date = res_date;
	}



	public String getRes_time() {
		return res_time;
	}



	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}



	public String getPet_name() {
		return pet_name;
	}



	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}



	public String getPet_sex() {
		return pet_sex;
	}



	public void setPet_sex(String pet_sex) {
		this.pet_sex = pet_sex;
	}



	public int getPet_age() {
		return pet_age;
	}



	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}



	public String getB_type() {
		return b_type;
	}



	public void setB_type(String b_type) {
		this.b_type = b_type;
	}



	public float getPet_weight() {
		return pet_weight;
	}



	public void setPet_weight(float pet_weight) {
		this.pet_weight = pet_weight;
	}



	public String getPet_types() {
		return pet_types;
	}



	public void setPet_types(String pet_types) {
		this.pet_types = pet_types;
	}



	public String getRes_etc() {
		return res_etc;
	}



	public void setRes_etc(String res_etc) {
		this.res_etc = res_etc;
	}



	public Date getR_res_date() {
		return r_res_date;
	}



	public void setR_res_date(Date r_res_date) {
		this.r_res_date = r_res_date;
	}



	public Date getR_receipt_date() {
		return r_receipt_date;
	}



	public void setR_receipt_date(Date r_receipt_date) {
		this.r_receipt_date = r_receipt_date;
	}



	public String getR_hos_name() {
		return r_hos_name;
	}



	public void setR_hos_name(String r_hos_name) {
		this.r_hos_name = r_hos_name;
	}



	public String getR_res_code() {
		return r_res_code;
	}



	public void setR_res_code(String r_res_code) {
		this.r_res_code = r_res_code;
	}



	public String getR_hos_code() {
		return r_hos_code;
	}



	public void setR_hos_code(String r_hos_code) {
		this.r_hos_code = r_hos_code;
	}



	public String getTx_code() {
		return tx_code;
	}



	public void setTx_code(String tx_code) {
		this.tx_code = tx_code;
	}



	public String getTx_res_code() {
		return tx_res_code;
	}



	public void setTx_res_code(String tx_res_code) {
		this.tx_res_code = tx_res_code;
	}



	public Date getTx_reserv_day() {
		return tx_reserv_day;
	}



	public void setTx_reserv_day(Date tx_reserv_day) {
		this.tx_reserv_day = tx_reserv_day;
	}



	public String getTx_dep() {
		return tx_dep;
	}



	public void setTx_dep(String tx_dep) {
		this.tx_dep = tx_dep;
	}



	public String getTx_arr() {
		return tx_arr;
	}



	public void setTx_arr(String tx_arr) {
		this.tx_arr = tx_arr;
	}



	public String getTx_number() {
		return tx_number;
	}



	public void setTx_number(String tx_number) {
		this.tx_number = tx_number;
	}



	public String getTx_name() {
		return tx_name;
	}



	public void setTx_name(String tx_name) {
		this.tx_name = tx_name;
	}



	public String getTx_local() {
		return tx_local;
	}



	public void setTx_local(String tx_local) {
		this.tx_local = tx_local;
	}



	public String getTx_tel() {
		return tx_tel;
	}



	public void setTx_tel(String tx_tel) {
		this.tx_tel = tx_tel;
	}



	public String getTtx_code() {
		return ttx_code;
	}



	public void setTtx_code(String ttx_code) {
		this.ttx_code = ttx_code;
	}



	public String getTtx_res_code() {
		return ttx_res_code;
	}



	public void setTtx_res_code(String ttx_res_code) {
		this.ttx_res_code = ttx_res_code;
	}



	public Date getTtx_reserv_day() {
		return ttx_reserv_day;
	}



	public void setTtx_reserv_day(Date ttx_reserv_day) {
		this.ttx_reserv_day = ttx_reserv_day;
	}



	public String getTtx_dep() {
		return ttx_dep;
	}



	public void setTtx_dep(String ttx_dep) {
		this.ttx_dep = ttx_dep;
	}



	public String getTtx_arr() {
		return ttx_arr;
	}



	public void setTtx_arr(String ttx_arr) {
		this.ttx_arr = ttx_arr;
	}



	public String getTtx_number() {
		return ttx_number;
	}



	public void setTtx_number(String ttx_number) {
		this.ttx_number = ttx_number;
	}



	public String getTtx_name() {
		return ttx_name;
	}



	public void setTtx_name(String ttx_name) {
		this.ttx_name = ttx_name;
	}



	public String getTtx_tel() {
		return ttx_tel;
	}



	public void setTtx_tel(String ttx_tel) {
		this.ttx_tel = ttx_tel;
	}



	public String getTx_img() {
		return tx_img;
	}



	public void setTx_img(String tx_img) {
		this.tx_img = tx_img;
	}



	public String getTx_restime() {
		return tx_restime;
	}



	public void setTx_restime(String tx_restime) {
		this.tx_restime = tx_restime;
	}



	public String getTx_intro() {
		return tx_intro;
	}



	public void setTx_intro(String tx_intro) {
		this.tx_intro = tx_intro;
	}



	public String getRes_name() {
		return res_name;
	}



	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}



	public String getHos_thum() {
		return hos_thum;
	}



	public void setHos_thum(String hos_thum) {
		this.hos_thum = hos_thum;
	}



}
