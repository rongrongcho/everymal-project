package com.myspring.stsproject.admin.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.admin.vo.AdminVO;

public interface AdminService {
	
	public Map<String, Object> listApps(Map<String, Object> pagingMap)throws DataAccessException;
	public int appCount() throws DataAccessException;
	public void approvalHosM(String[] chk) throws DataAccessException;
	public Map<String, Object> memberList(Map<String, Object> pagingMap) throws DataAccessException;
	public AdminVO viewApplication(String hos_code)  throws Exception;
}
