package com.myspring.stsproject.withdrawal.vo;

import org.springframework.stereotype.Component;

@Component("withdrawalVO")
public class WithdrawalVO {
	private String id;
	private String hos_enabled;
	private String user_enabled;
	private String hos_id;
	private String user_id;
	
	public WithdrawalVO() {
		
	}
	

	public WithdrawalVO(String id, String hos_enabled, String user_enabled, String hos_id, String user_id) {
		super();
		this.id = id;
		this.hos_enabled = hos_enabled;
		this.user_enabled = user_enabled;
		this.hos_id = hos_id;
		this.user_id = user_id;
	}


	public String getHos_id() {
		return hos_id;
	}


	public void setHos_id(String hos_id) {
		this.hos_id = hos_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getHos_enabled() {
		return hos_enabled;
	}

	public void setHos_enabled(String hos_enabled) {
		this.hos_enabled = hos_enabled;
	}

	public String getUser_enabled() {
		return user_enabled;
	}

	public void setUser_enabled(String user_enabled) {
		this.user_enabled = user_enabled;
	}

	
	public WithdrawalVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
