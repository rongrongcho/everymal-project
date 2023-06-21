package hosApp;

import java.sql.Date;

public class HosAppVO {
	private String rm_code;
	private String hos_code;
	private String hos_name;
	private Date rm_date;
	private String hos_id;
	private String rm_status;
	private String rm_say;
	private String hos_status;
	private String hos_Thum;
	private String himg1;
	private String himg2;
	private String himg3;
	private Long hos_dno;
	//===========================
	public HosAppVO() {
		super();
	}
	public HosAppVO(String rm_code, String hos_code, String hos_name, Date rm_date, String hos_id, String rm_status,
			String rm_say, String hos_status, String hos_Thum, String himg1, String himg2, String himg3, long hos_dno) {
		super();
		this.rm_code = rm_code;
		this.hos_code = hos_code;
		this.hos_name = hos_name;
		this.rm_date = rm_date;
		this.hos_id = hos_id;
		this.rm_status = rm_status;
		this.rm_say = rm_say;
		this.hos_status = hos_status;
		this.hos_Thum = hos_Thum;
		this.himg1 = himg1;
		this.himg2 = himg2;
		this.himg3 = himg3;
		this.hos_dno = hos_dno;
	}
	public String getRm_code() {
		return rm_code;
	}
	public void setRm_code(String rm_code) {
		this.rm_code = rm_code;
	}
	public String getHos_code() {
		return hos_code;
	}
	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}
	public String getHos_name() {
		return hos_name;
	}
	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}
	public Date getRm_date() {
		return rm_date;
	}
	public void setRm_date(Date rm_date) {
		this.rm_date = rm_date;
	}
	public String getHos_id() {
		return hos_id;
	}
	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
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
	public String getHos_status() {
		return hos_status;
	}
	public void setHos_status(String hos_status) {
		this.hos_status = hos_status;
	}
	public String getHos_Thum() {
		return hos_Thum;
	}
	public void setHos_Thum(String hos_Thum) {
		this.hos_Thum = hos_Thum;
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
	public long getHos_dno() {
		return hos_dno;
	}
	public void setHos_dno(long hos_dno) {
		this.hos_dno = hos_dno;
	}

	
	
	

}
