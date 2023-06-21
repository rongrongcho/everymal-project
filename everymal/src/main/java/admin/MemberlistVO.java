package admin;

import java.sql.Date;

public class MemberlistVO {
       private String id;
       private Date j_date;
       private String genre;
       private String enabled;
       private String code;
       

	//빈 생성자
       public MemberlistVO() {
		
	}

    
	public MemberlistVO(String id, Date j_date, String genre, String enabled, String code) {
		super();
		this.id = id;
		this.j_date = j_date;
		this.genre = genre;
		this.enabled = enabled;
		this.code = code;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getJ_date() {
		return j_date;
	}


	public void setJ_date(Date j_date) {
		this.j_date = j_date;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
       
       
}
