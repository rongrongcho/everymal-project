package com.myspring.stsproject.hosReservation.vo;

import org.springframework.stereotype.Component;

@Component("reservationVO")
public class ReservationVO {
	private String res_code;
	private String receipt_date;
	private String user_name;
	private String user_tel;
	private String pet_name;
	private int pet_age;
	private String pet_sex;
	private String hos_name;
	private String res_date;
	private String res_time;
	private String hos_sub;
	private String res_etc;
	private String hos_code;
	private String pet_code;
	private String user_code;
	private String pet_types;
	private String pet_number;
	private String b_type;
	private float pet_weight;
	private String pet_etc;
	private String user_id;
	
	public ReservationVO() {
		
	}
	


	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public String getReceipt_date() {
		return receipt_date;
	}

	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
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

	public String getPet_name() {
		return pet_name;
	}

	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}

	public int getPet_age() {
		return pet_age;
	}

	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}

	public String getPet_sex() {
		return pet_sex;
	}

	public void setPet_sex(String pet_sex) {
		this.pet_sex = pet_sex;
	}

	public String getHos_name() {
		return hos_name;
	}

	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public String getHos_sub() {
		return hos_sub;
	}

	public void setHos_sub(String hos_sub) {
		this.hos_sub = hos_sub;
	}

	public String getRes_etc() {
		return res_etc;
	}

	public void setRes_etc(String res_etc) {
		this.res_etc = res_etc;
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

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getPet_types() {
		return pet_types;
	}

	public void setPet_types(String pet_types) {
		this.pet_types = pet_types;
	}

	public String getPet_number() {
		return pet_number;
	}

	public void setPet_number(String pet_number) {
		this.pet_number = pet_number;
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




	public String getPet_etc() {
		return pet_etc;
	}




	public void setPet_etc(String pet_etc) {
		this.pet_etc = pet_etc;
	}




	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public ReservationVO(String res_code, String receipt_date, String user_name, String user_tel, String pet_name,
			int pet_age, String pet_sex, String hos_name, String res_date, String res_time, String hos_sub,
			String res_etc, String hos_code, String pet_code, String user_code, String pet_types, String pet_number,
			String b_type, float pet_weight, String user_id) {
		super();
		this.res_code = res_code;
		this.receipt_date = receipt_date;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.hos_name = hos_name;
		this.res_date = res_date;
		this.res_time = res_time;
		this.hos_sub = hos_sub;
		this.res_etc = res_etc;
		this.hos_code = hos_code;
		this.pet_code = pet_code;
		this.user_code = user_code;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.user_id = user_id;
	}




	public ReservationVO(String user_code, String user_name, String user_tel, String pet_name, String pet_code,
			String pet_number, String pet_sex, String pet_types, int pet_age, String b_type, String res_date,
			String res_time, String res_etc, float pet_weight, String hos_name, String hos_code, String hos_sub) {
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.hos_name = hos_name;
		this.res_date = res_date;
		this.res_time = res_time;
		this.hos_sub = hos_sub;
		this.res_etc = res_etc;
		this.hos_code = hos_code;
		this.pet_code = pet_code;
		this.user_code = user_code;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.pet_weight = pet_weight;
		this.b_type = b_type;
	}

	

	
}

