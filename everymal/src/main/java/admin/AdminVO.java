package admin;

import java.sql.Date;

public class AdminVO {

	//병원등록관리 페이지를 위한 변수 선언
	
	
	private String hos_name; //신청 병원이름
	private String hos_id; //신청 병원 아이디 
	private String rm_code; // 신청 코드
	private String rm_chk; // 선택 여부 
	private String rm_status; // 진행 상황 
	private Date rm_date;
	private String hos_code; 
	private String rm_say;
	//=============================================
	public AdminVO() {
		super();
	}
	public AdminVO(String hos_name, String hos_id, String rm_code, String rm_chk, String rm_status, Date rm_date,
			String hos_code, String rm_say) {
		super();
		this.hos_name = hos_name;
		this.hos_id = hos_id;
		this.rm_code = rm_code;
		this.rm_chk = rm_chk;
		this.rm_status = rm_status;
		this.rm_date = rm_date;
		this.hos_code = hos_code;
		this.rm_say = rm_say;
	}
	public String getHos_name() {
		return hos_name;
	}
	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}
	public String getHos_id() {
		return hos_id;
	}
	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
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
	public String getRm_status() {
		return rm_status;
	}
	public void setRm_status(String rm_status) {
		this.rm_status = rm_status;
	}
	public Date getRm_date() {
		return rm_date;
	}
	public void setRm_date(Date rm_date) {
		this.rm_date = rm_date;
	}
	public String getHos_code() {
		return hos_code;
	}
	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}
	public String getRm_say() {
		return rm_say;
	}
	public void setRm_say(String rm_say) {
		this.rm_say = rm_say;
	}


	

	
	
	
	
	
	
	
	
	
}
