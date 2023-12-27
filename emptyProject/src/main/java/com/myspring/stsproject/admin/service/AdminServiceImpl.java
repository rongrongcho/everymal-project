package com.myspring.stsproject.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.admin.dao.AdminDAO;
import com.myspring.stsproject.admin.vo.AdminVO;
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminVO adminVO;
	
	@Autowired
	private AdminDAO adminDAO;


	@Override
	public Map<String, Object> listApps(Map<String, Object> pagingMap) throws DataAccessException {
		Map<String,Object> rmMap=new HashMap<String, Object>();
		List<AdminVO> rmList=null;
	    String orderBy = String.valueOf(pagingMap.get("orderby"));
	    int totalApps=0;
	    if ("rmTotList".equals(orderBy)) {
	        rmList = adminDAO.selectRMList_tot(pagingMap);
	        totalApps=adminDAO.allApps();
	    } else if ("uncheckedRM".equals(orderBy)) {
	        rmList = adminDAO.selectRMList_unchecked(pagingMap);
	        totalApps=adminDAO.ukApps();
	    } else if ("checkedRM".equals(orderBy)) {
	        rmList = adminDAO.selectRMList_checked(pagingMap);
	        totalApps=adminDAO.ckApps();
	    } else if ("confirmed".equals(orderBy)) {
	        rmList = adminDAO.selectRMList_confirmed(pagingMap);
	        totalApps=adminDAO.cfApps();
	    } else {
	    	rmList=adminDAO.selectRMList_tot(pagingMap);
	    	totalApps=adminDAO.allApps();
	    }

		rmMap.put("rmList",rmList);
		rmMap.put("totalApps", totalApps);
		
		return rmMap;
	}

	@Override
	public int appCount() throws DataAccessException {
	      int app_count= adminDAO.appCount();
	      return app_count;
	}

	@Override
	public void approvalHosM(String[] chk) throws DataAccessException {
		  adminDAO.appManyHos_hostbl(chk);
		  adminDAO.appManyHos_rmtbl(chk);
	}
	
	
	@Override
	public Map<String, Object> memberList(Map<String, Object> pagingMap) throws DataAccessException {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    List<AdminVO> userList = null;
	    String orderBy = String.valueOf(pagingMap.get("orderby"));
	    int totalList=0;

	    if ("list".equals(orderBy)) {
	        userList = adminDAO.memberList(pagingMap);
	        totalList=adminDAO.allmember();
	    } else if ("glist".equals(orderBy)) {
	        userList = adminDAO.userMemberList(pagingMap);
	        totalList=adminDAO.gmember();
	    } else if ("hlist".equals(orderBy)) {
	        userList = adminDAO.hosMemberList(pagingMap);
	        totalList=adminDAO.hosmember();
	    } else if ("elist".equals(orderBy)) {
	        userList = adminDAO.enabledMemberList(pagingMap);
	        totalList=adminDAO.enabledmember();
	    } else if ("alist".equals(orderBy)) {
	        userList = adminDAO.abledMemberList(pagingMap);
	        totalList=adminDAO.abledmember();
	    } else {
	    	userList=adminDAO.memberList(pagingMap);
	    	totalList=adminDAO.allmember();
	    }

	    resultMap.put("userList", userList);
	    resultMap.put("totalList", totalList);
	    return resultMap;
	}

	@Override
	public AdminVO viewApplication(String hos_code,String rm_code) throws Exception {
		
		return adminDAO.viewAppAdmin(hos_code,rm_code);
	}

}
