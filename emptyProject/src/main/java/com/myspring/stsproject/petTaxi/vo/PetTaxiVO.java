package com.myspring.stsproject.petTaxi.vo;

import org.springframework.stereotype.Component;

@Component("petTaxiVO")

public class PetTaxiVO {
		private String tx_tel; 
		private String tx_number; 
		private String tx_name; 
		private String tx_local; 
		private String tx_code;
		private String tx_intro; 
		private String tx_img;
		

		private String tx_res_code;
		private String tx_reserv_day; 
		private String tx_dep; 
		private String tx_arr; 
		private String tx_restime; 
		private String res_name;
		private String user_code; 



		public PetTaxiVO() {
			super();
		}


		public PetTaxiVO(String tx_tel, String tx_number, String tx_name, String tx_local, String tx_code, String tx_intro,
				String tx_img, String tx_res_code, String tx_reserv_day, String tx_dep, String tx_arr, String tx_restime,
				String res_name, String user_code) {
			super();
			this.tx_tel = tx_tel;
			this.tx_number = tx_number;
			this.tx_name = tx_name;
			this.tx_local = tx_local;
			this.tx_code = tx_code;
			this.tx_intro = tx_intro;
			this.tx_img = tx_img;
			this.tx_res_code = tx_res_code;
			this.tx_reserv_day = tx_reserv_day;
			this.tx_dep = tx_dep;
			this.tx_arr = tx_arr;
			this.tx_restime = tx_restime;
			this.res_name = res_name;
			this.user_code = user_code;
		}


		public String getTx_tel() {
			return tx_tel;
		}


		public void setTx_tel(String tx_tel) {
			this.tx_tel = tx_tel;
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


		public String getTx_code() {
			return tx_code;
		}


		public void setTx_code(String tx_code) {
			this.tx_code = tx_code;
		}


		public String getTx_intro() {
			return tx_intro;
		}


		public void setTx_intro(String tx_intro) {
			this.tx_intro = tx_intro;
		}


		public String getTx_img() {
			return tx_img;
		}


		public void setTx_img(String tx_img) {
			this.tx_img = tx_img;
		}


		public String getTx_res_code() {
			return tx_res_code;
		}


		public void setTx_res_code(String tx_res_code) {
			this.tx_res_code = tx_res_code;
		}


		public String getTx_reserv_day() {
			return tx_reserv_day;
		}


		public void setTx_reserv_day(String tx_reserv_day) {
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


		public String getTx_restime() {
			return tx_restime;
		}


		public void setTx_restime(String tx_restime) {
			this.tx_restime = tx_restime;
		}


		public String getRes_name() {
			return res_name;
		}


		public void setRes_name(String res_name) {
			this.res_name = res_name;
		}


		public String getUser_code() {
			return user_code;
		}


		public void setUser_code(String user_code) {
			this.user_code = user_code;
		}
		

}
