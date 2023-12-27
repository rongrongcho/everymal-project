package com.myspring.stsproject.animal.vo;

import org.springframework.stereotype.Component;

@Component("animalVO")
public class AnimalVO {

	private String user_code;
	private String pet_code;
	private String pet_name;
	private int pet_age;
	private String pet_sex;
	private String pet_types;
	private String pet_number;
	private String b_type;
	private float pet_weight;
	private String pet_etc;
	
	public AnimalVO() {
		super();
	}
	
	
	
	public AnimalVO(String user_code2, String pet_name2, int pet_age2, String pet_sex2, String pet_types2, String pet_number2, String b_type2, float pet_weight2, String pet_etc2) {
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getPet_code() {
		return pet_code;
	}
	public void setPet_code(String pet_code) {
		this.pet_code = pet_code;
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
	public AnimalVO(String user_code, String pet_code, String pet_name, int pet_age, String pet_sex, String pet_types,
			String pet_number, String b_type, float pet_weight, String pet_etc) {
		super();
		this.user_code = user_code;
		this.pet_code = pet_code;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_etc = pet_etc;
	}
	
}
