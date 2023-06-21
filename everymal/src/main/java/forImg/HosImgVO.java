package forImg;

public class HosImgVO {
	
	
	private String hos_code;
	private String img_code;
	private String himg1;
	private String himg2;
	private String himg3;
	
	
	//======================
	
	private String hos_pro;
	private String hos_thum; 
	
	//=========================
	
	
	public HosImgVO() {
		super();
	}

	public HosImgVO(String hos_code, String img_code, String himg1, String himg2, String himg3, String hos_pro,
			String hos_thum) {
		super();
		this.hos_code = hos_code;
		this.img_code = img_code;
		this.himg1 = himg1;
		this.himg2 = himg2;
		this.himg3 = himg3;
		this.hos_pro = hos_pro;
		this.hos_thum = hos_thum;
	}

	public String getHos_code() {
		return hos_code;
	}

	public void setHos_code(String hos_code) {
		this.hos_code = hos_code;
	}

	public String getImg_code() {
		return img_code;
	}

	public void setImg_code(String img_code) {
		this.img_code = img_code;
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

	public String getHos_pro() {
		return hos_pro;
	}

	public void setHos_pro(String hos_pro) {
		this.hos_pro = hos_pro;
	}

	public String getHos_thum() {
		return hos_thum;
	}

	public void setHos_thum(String hos_thum) {
		this.hos_thum = hos_thum;
	}
	
	

}
