package com.myspring.stsproject.hosReviewInfo.vo;
import java.sql.Date;

import org.springframework.stereotype.Component;
@Component("reviewVO")
public class ReviewVO {
	
	       private String rv_code; //리뷰코드
	       private String rv_title; //리뷰 제목
	       private String rv_text;; //리뷰 내용
	       private int rv_rate;//리뷰 별점
	       private String hos_name;//병원이름
	       private String hos_code;//병원코드
	       private String res_code; //예약 코드
	       private Date rv_date;
	       private String [] items;
	       private String rv_imgName;//첨부이미지 이름
	       
	      /* public String getHos_id() {
			return hos_id;
		}
		public void setHos_id(String hos_id) {
			this.hos_id = hos_id;
		}*/
	       





		public String[] getItems() {
			return items;
		}


		public String getRv_imgName() {
			return rv_imgName;
		}


		public void setRv_imgName(String rv_imgName) {
			this.rv_imgName = rv_imgName;
		}


		public void setItems(String[] items) {
			this.items = items;
		}


		//빈 생성자
	       public ReviewVO() {
			
		}
	       
	       
	       //jsp에 뿌려줄 정보만 담는 생성자 (나중에 만들기)   



		public String getRv_code() {
			return rv_code;
		}


		public ReviewVO(String rv_code, String rv_title, Date rv_date, int rv_rate, String rv_text ) {
			this.rv_code = rv_code;
			//this.hos_code=hos_code;
			//this.hos_name=hos_name;
			this.rv_title = rv_title;
			this.rv_rate = rv_rate;
			this.rv_date = rv_date;
			this.rv_text=rv_text;
		}


		public ReviewVO(String rv_code, String hos_code, String hos_name, Date rv_date) {
			this.rv_code = rv_code;
			this.hos_code=hos_code;
			this.hos_name=hos_name;
			this.rv_date = rv_date;	
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


		public String getHos_name() {
			return hos_name;
		}


		public void setHos_name(String hos_name) {
			this.hos_name = hos_name;
		}


		public String getHos_code() {
			return hos_code;
		}


		public void setHos_code(String hos_code) {
			this.hos_code = hos_code;
		}


		public String getRes_code() {
			return res_code;
		}


		public void setRes_code(String res_code) {
			this.res_code = res_code;
		}


		public Date getRv_date() {
			return rv_date;
		}


		public void setRv_date(Date rv_date) {
			this.rv_date = rv_date;
		}    
	       
	       
	       
	       
	       

		
	       
	       
	       

		

	
	       
	   
	       
	

}
