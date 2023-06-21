package hosResInfo;

import java.sql.Date;

public class ResVO {
       private String res_code; //예약코드
       private String user_name; //일반회원 이름
       private String user_tel; //사용자 전화번호
       private String pet_name;//반려동물이름
       private int pet_age;//반려동물나이
       private String pet_sex;//반려동물 성별
       private String hos_name;//병원이름
       private Date res_date;//예약희망날짜
       private String res_time;//희망예약시간
       private String hos_sub;//진료과목
       private String res_etc;//요구사항 및 특이사항
       private String hos_code;//병원코드
       private String pet_code;//반려동물코드
       private String user_code;//일반회원코드
       private Date receipt_date;//예약 신청 날짜
       private String user_id;
       private String hos_img;
       private String b_type;
       private String pet_types;//반려동물 종
       private float pet_weight;
       private int res_count; //예약 개수
       private String hos_id; //병원 id
       private String pet_number;
       
       
       public String getHos_id() {
		return hos_id;
	}
	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
	}
	//빈 생성자
       public ResVO() {
		
	}
public ResVO(String res_code, String pet_types, String user_name, Date receipt_date) {
	this.res_code = res_code;
	this.pet_types = pet_types;
	this.user_name = user_name;
	this.receipt_date = receipt_date;
	
	
}
public String getRes_code() {
	return res_code;
}
public void setRes_code(String res_code) {
	this.res_code = res_code;
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
public Date getReceipt_date() {
	return receipt_date;
}
public void setReceipt_date(Date receipt_date) {
	this.receipt_date = receipt_date;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getHos_img() {
	return hos_img;
}
public void setHos_img(String hos_img) {
	this.hos_img = hos_img;
}
public String getB_type() {
	return b_type;
}
public void setB_type(String b_type) {
	this.b_type = b_type;
}
public String getPet_types() {
	return pet_types;
}
public void setPet_types(String pet_types) {
	this.pet_types = pet_types;
}
public float getPet_weight() {
	return pet_weight;
}
public void setPet_weight(float pet_weight) {
	this.pet_weight = pet_weight;
}
public int getRes_count() {
	return res_count;
}
public void setRes_count(int res_count) {
	this.res_count = res_count;
}
public ResVO(String res_code, String user_name, String user_tel, String pet_name, int pet_age, String pet_sex,
		String hos_name, Date res_date, String res_time, String hos_sub, String res_etc, String hos_code,
		String pet_code, String user_code, Date receipt_date, String user_id, String hos_img, String b_type,
		String pet_types, float pet_weight, int res_count, String pet_number, String hos_id) {
	super();
	this.res_code = res_code;
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
	this.receipt_date = receipt_date;
	this.user_id = user_id;
	this.hos_img = hos_img;
	this.b_type = b_type;
	this.pet_types = pet_types;
	this.pet_weight = pet_weight;
	this.res_count = res_count;
	this.hos_id = hos_id;
	this.pet_number=pet_number;
}
public String getPet_number() {
	return pet_number;
}
public void setPet_number(String pet_number) {
	this.pet_number = pet_number;
}







   
       
}
