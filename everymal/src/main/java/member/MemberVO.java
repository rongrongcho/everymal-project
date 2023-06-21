package member;

import java.util.Date;

public class MemberVO {
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
	private Date j_date;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String user_id, String user_pwd, String user_name, String user_tel, String user_email,
			String user_addr1, String user_addr2, String user_zipcode) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.user_zipcode = user_zipcode;
	}

	public MemberVO(String user_id, String user_pwd, String user_name, String user_tel, String user_email,
			String user_addr1, String user_addr2, Date j_date) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.j_date = j_date;
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

	public Date getJ_date() {
		return j_date;
	}

	public void setJ_date(Date j_date) {
		this.j_date = j_date;
	}

}
