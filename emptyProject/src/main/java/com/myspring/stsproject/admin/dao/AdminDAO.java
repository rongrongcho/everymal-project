package com.myspring.stsproject.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.admin.vo.AdminVO;

public interface AdminDAO {
	
	public List selectRMList_tot(Map<String, Object> pagingMap) throws DataAccessException;
	public List selectRMList_unchecked(Map<String, Object> pagingMap) throws DataAccessException;
	public List selectRMList_checked(Map<String, Object> pagingMap) throws DataAccessException;
	public List selectRMList_confirmed(Map<String, Object> pagingMap) throws DataAccessException;
	public int allApps() throws DataAccessException;
	public int ukApps() throws DataAccessException;
	public int ckApps() throws DataAccessException;
	public int cfApps() throws DataAccessException;

	public int appCount() throws DataAccessException;
	public void appManyHos_hostbl(String[] chk ) throws DataAccessException;
	public void appManyHos_rmtbl(String[] chk ) throws DataAccessException;
	
	public List memberList(Map<String, Object> pagingMap) throws DataAccessException;
	public List hosMemberList(Map<String, Object> pagingMap) throws DataAccessException;
	public List userMemberList(Map<String, Object> pagingMap) throws DataAccessException;
	public List abledMemberList(Map<String, Object> pagingMap) throws DataAccessException;
	public List enabledMemberList(Map<String, Object> pagingMap) throws DataAccessException;
	public int allmember() throws DataAccessException;
	public int enabledmember() throws DataAccessException;
	public int abledmember() throws DataAccessException;
	public int gmember() throws DataAccessException;
	public int hosmember() throws DataAccessException;
	
	public AdminVO viewAppAdmin(String hos_code,String rm_code)  throws Exception;
	public void checkHos(String hos_code) throws Exception;
	public void approvalRM(String hos_code) throws Exception;
	public void rjRM(Map<String, String> rjMap) throws Exception;
}
