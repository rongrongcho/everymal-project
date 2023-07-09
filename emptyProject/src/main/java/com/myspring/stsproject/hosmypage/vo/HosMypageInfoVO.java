package com.myspring.stsproject.hosmypage.vo;

import org.springframework.stereotype.Component;

@Component("hosmypageinfoVO")
public class HosMypageInfoVO {
		private String hos_code;
		private String hos_pro;
		private String hos_id;
		private String hos_username;
		private String hos_usertel;
		private long hos_dno;
		private String hos_name;
		private String hos_tel;
		private String hos_zipcode;
		private String hos_addr1;
		private String hos_addr2;
		private String hos_email;
		private String hos_intro;
		private String hos_pwd;
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
		private String usertel_front;
		private String usertel_end;
		private String hos_status;
		private String himg1;
		private String himg2;
		private String himg3;
		
		private String rm_code;
		private String rm_chk;
		private String rm_date;
		private String rm_status;
		private String rm_say;
		
		
		
		
		public HosMypageInfoVO(String hos_code, String hos_pro, String hos_id, String hos_username, String hos_usertel,
				long hos_dno, String hos_name, String hos_tel, String hos_zipcode, String hos_addr1, String hos_addr2,
				String hos_email, String hos_intro, String hos_pwd, String hos_24, String hos_365, String hos_time_s,
				String hos_time_e, String hos_dog, String hos_cat, String hos_small, String hos_fish, String hos_bird,
				String hos_rep, String hos_etc, String hos_gs, String hos_im, String hos_em, String hos_rm,
				String hos_vac, String tel_front, String tel_end, String usertel_front, String usertel_end,
				String hos_status, String himg1, String himg2, String himg3, String rm_code, String rm_chk,
				String rm_date, String rm_status, String rm_say) {
			super();
			this.hos_code = hos_code;
			this.hos_pro = hos_pro;
			this.hos_id = hos_id;
			this.hos_username = hos_username;
			this.hos_usertel = hos_usertel;
			this.hos_dno = hos_dno;
			this.hos_name = hos_name;
			this.hos_tel = hos_tel;
			this.hos_zipcode = hos_zipcode;
			this.hos_addr1 = hos_addr1;
			this.hos_addr2 = hos_addr2;
			this.hos_email = hos_email;
			this.hos_intro = hos_intro;
			this.hos_pwd = hos_pwd;
			this.hos_24 = hos_24;
			this.hos_365 = hos_365;
			this.hos_time_s = hos_time_s;
			this.hos_time_e = hos_time_e;
			this.hos_dog = hos_dog;
			this.hos_cat = hos_cat;
			this.hos_small = hos_small;
			this.hos_fish = hos_fish;
			this.hos_bird = hos_bird;
			this.hos_rep = hos_rep;
			this.hos_etc = hos_etc;
			this.hos_gs = hos_gs;
			this.hos_im = hos_im;
			this.hos_em = hos_em;
			this.hos_rm = hos_rm;
			this.hos_vac = hos_vac;
			this.tel_front = tel_front;
			this.tel_end = tel_end;
			this.usertel_front = usertel_front;
			this.usertel_end = usertel_end;
			this.hos_status = hos_status;
			this.himg1 = himg1;
			this.himg2 = himg2;
			this.himg3 = himg3;
			this.rm_code = rm_code;
			this.rm_chk = rm_chk;
			this.rm_date = rm_date;
			this.rm_status = rm_status;
			this.rm_say = rm_say;
		}

		public String getRm_code() {
			return rm_code;
		}

		public void setRm_code(String rm_code) {
			this.rm_code = rm_code;
		}

		public String getRm_chk() {
			return rm_chk;
		}

		public void setRm_chk(String rm_chk) {
			this.rm_chk = rm_chk;
		}

		public String getRm_date() {
			return rm_date;
		}

		public void setRm_date(String rm_date) {
			this.rm_date = rm_date;
		}

		public String getRm_status() {
			return rm_status;
		}

		public void setRm_status(String rm_status) {
			this.rm_status = rm_status;
		}

		public String getRm_say() {
			return rm_say;
		}

		public void setRm_say(String rm_say) {
			this.rm_say = rm_say;
		}

		public HosMypageInfoVO() {
			
		}
		
		public HosMypageInfoVO(String hos_code, String himg1, String himg2, String himg3) {
			super();
			this.hos_code=hos_code;
			this.himg1 = himg1;
			this.himg2 = himg2;
			this.himg3 = himg3;
		}

		public HosMypageInfoVO(String hos_code, String hos_pro, String hos_id, String hos_username, String hos_usertel,
				long hos_dno, String hos_name, String hos_tel, String hos_zipcode, String hos_addr1, String hos_addr2,
				String hos_email, String hos_intro, String hos_pwd, String hos_24, String hos_365, String hos_time_s,
				String hos_time_e, String hos_dog, String hos_cat, String hos_small, String hos_fish, String hos_bird,
				String hos_rep, String hos_etc, String hos_gs, String hos_im, String hos_em, String hos_rm,
				String hos_vac, String tel_front, String tel_end, String usertel_front, String usertel_end,
				String hos_status, String himg1, String himg2, String himg3) {
			super();
			this.hos_code = hos_code;
			this.hos_pro = hos_pro;
			this.hos_id = hos_id;
			this.hos_username = hos_username;
			this.hos_usertel = hos_usertel;
			this.hos_dno = hos_dno;
			this.hos_name = hos_name;
			this.hos_tel = hos_tel;
			this.hos_zipcode = hos_zipcode;
			this.hos_addr1 = hos_addr1;
			this.hos_addr2 = hos_addr2;
			this.hos_email = hos_email;
			this.hos_intro = hos_intro;
			this.hos_pwd = hos_pwd;
			this.hos_24 = hos_24;
			this.hos_365 = hos_365;
			this.hos_time_s = hos_time_s;
			this.hos_time_e = hos_time_e;
			this.hos_dog = hos_dog;
			this.hos_cat = hos_cat;
			this.hos_small = hos_small;
			this.hos_fish = hos_fish;
			this.hos_bird = hos_bird;
			this.hos_rep = hos_rep;
			this.hos_etc = hos_etc;
			this.hos_gs = hos_gs;
			this.hos_im = hos_im;
			this.hos_em = hos_em;
			this.hos_rm = hos_rm;
			this.hos_vac = hos_vac;
			this.tel_front = tel_front;
			this.tel_end = tel_end;
			this.usertel_front = usertel_front;
			this.usertel_end = usertel_end;
			this.hos_status = hos_status;
			this.himg1 = himg1;
			this.himg2 = himg2;
			this.himg3 = himg3;
		}



		public HosMypageInfoVO(String hos_code, String hos_pro, String hos_id, String hos_username, String hos_usertel,
				long hos_dno, String hos_name, String hos_tel, String hos_zipcode, String hos_addr1, String hos_addr2, String hos_email, String hos_intro,
				String hos_pwd, String hos_24, String hos_365, String hos_time_s, String hos_time_e,
				String hos_dog, String hos_cat, String hos_small, String hos_fish, String hos_bird, String hos_rep,
				String hos_etc, String hos_gs, String hos_im, String hos_em, String hos_rm, String hos_vac, 
				String tel_front, String tel_end, String usertel_front, String usertel_end) {
			super();
			this.hos_code = hos_code;
			this.hos_pro = hos_pro;
			this.hos_id = hos_id;
			this.hos_username = hos_username;
			this.hos_usertel = hos_usertel;
			this.hos_dno = hos_dno;
			this.hos_name = hos_name;
			this.hos_tel = hos_tel;
			this.hos_zipcode = hos_zipcode;
			this.hos_addr1 = hos_addr1;
			this.hos_addr2 = hos_addr2;
			this.hos_email = hos_email;
			this.hos_intro = hos_intro;
			this.hos_pwd = hos_pwd;
			this.hos_24 = hos_24;
			this.hos_365 = hos_365;
			this.hos_time_s = hos_time_s;
			this.hos_time_e = hos_time_e;
			this.hos_dog = hos_dog;
			this.hos_cat = hos_cat;
			this.hos_small = hos_small;
			this.hos_fish = hos_fish;
			this.hos_bird = hos_bird;
			this.hos_rep = hos_rep;
			this.hos_etc = hos_etc;
			this.hos_gs = hos_gs;
			this.hos_im = hos_im;
			this.hos_em = hos_em;
			this.hos_rm = hos_rm;
			this.hos_vac = hos_vac;
			this.tel_front=tel_front;
			this.tel_end=tel_end;
			this.usertel_front=usertel_front;
			this.usertel_end=usertel_end;
		}
		
		public HosMypageInfoVO(String hos_code, String hos_pro, String hos_id, String hos_username, String hos_usertel,
				long hos_dno, String hos_name, String hos_tel, String hos_zipcode, String hos_addr1, String hos_addr2,
				String hos_email, String hos_intro, String hos_pwd, String hos_24, String hos_365, String hos_time_s,
				String hos_time_e, String hos_dog, String hos_cat, String hos_small, String hos_fish, String hos_bird,
				String hos_rep, String hos_etc, String hos_gs, String hos_im, String hos_em, String hos_rm, String hos_vac,
				String tel_front, String tel_end, String usertel_front, String usertel_end, String hos_status) {
			super();
			this.hos_code = hos_code;
			this.hos_pro = hos_pro;
			this.hos_id = hos_id;
			this.hos_username = hos_username;
			this.hos_usertel = hos_usertel;
			this.hos_dno = hos_dno;
			this.hos_name = hos_name;
			this.hos_tel = hos_tel;
			this.hos_zipcode = hos_zipcode;
			this.hos_addr1 = hos_addr1;
			this.hos_addr2 = hos_addr2;
			this.hos_email = hos_email;
			this.hos_intro = hos_intro;
			this.hos_pwd = hos_pwd;
			this.hos_24 = hos_24;
			this.hos_365 = hos_365;
			this.hos_time_s = hos_time_s;
			this.hos_time_e = hos_time_e;
			this.hos_dog = hos_dog;
			this.hos_cat = hos_cat;
			this.hos_small = hos_small;
			this.hos_fish = hos_fish;
			this.hos_bird = hos_bird;
			this.hos_rep = hos_rep;
			this.hos_etc = hos_etc;
			this.hos_gs = hos_gs;
			this.hos_im = hos_im;
			this.hos_em = hos_em;
			this.hos_rm = hos_rm;
			this.hos_vac = hos_vac;
			this.tel_front = tel_front;
			this.tel_end = tel_end;
			this.usertel_front = usertel_front;
			this.usertel_end = usertel_end;
			this.hos_status = hos_status;
		}

		
		public String getHimg1() {
			return himg1;
		}

		public void setHimg1(String himg1) {
			this.himg1 = himg1;
		}

		public String getHimg2() {
			return himg2;
		}

		public void setHimg2(String himg2) {
			this.himg2 = himg2;
		}

		public String getHimg3() {
			return himg3;
		}

		public void setHimg3(String himg3) {
			this.himg3 = himg3;
		}

		public String getHos_status() {
			return hos_status;
		}

		public void setHos_status(String hos_status) {
			this.hos_status = hos_status;
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

		public long getHos_dno() {
			return hos_dno;
		}

		public void setHos_dno(long hos_dno) {
			this.hos_dno = hos_dno;
		}

		public String getHos_name() {
			return hos_name;
		}

		public void setHos_name(String hos_name) {
			this.hos_name = hos_name;
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

		public String getHos_pwd() {
			return hos_pwd;
		}

		public void setHos_pwd(String hos_pwd) {
			this.hos_pwd = hos_pwd;
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

		public String getUsertel_front() {
			return usertel_front;
		}

		public void setUsertel_front(String usertel_front) {
			this.usertel_front = usertel_front;
		}

		public String getUsertel_end() {
			return usertel_end;
		}

		public void setUsertel_end(String usertel_end) {
			this.usertel_end = usertel_end;
		}


}
