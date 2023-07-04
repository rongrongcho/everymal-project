package com.myspring.stsproject.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.admin.vo.AdminVO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectRMList_tot(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
	    int section = (int)pagingMap.get("section");
	    int pageNum = (int)pagingMap.get("pageNum");
	    Map pagingM = new HashMap();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    rmList = sqlSession.selectList("mapper.admin.selectRMList_tot", pagingM);
		return rmList;
	}
	
	@Override
	public List selectRMList_unchecked(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
	    int section = (int)pagingMap.get("section");
	    int pageNum = (int)pagingMap.get("pageNum");
	    Map pagingM = new HashMap();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    rmList = sqlSession.selectList("mapper.admin.selectRMList_uc", pagingM);
		return rmList;
	}

	@Override
	public List selectRMList_checked(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
	    int section = (int)pagingMap.get("section");
	    int pageNum = (int)pagingMap.get("pageNum");
	    Map pagingM = new HashMap();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    rmList = sqlSession.selectList("mapper.admin.selectRMList_ck", pagingM);
		return rmList;
	}

	@Override
	public List selectRMList_confirmed(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
	    int section = (int)pagingMap.get("section");
	    int pageNum = (int)pagingMap.get("pageNum");
	    Map pagingM = new HashMap();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    rmList = sqlSession.selectList("mapper.admin.selectRMList_cf", pagingM);
		return rmList;
	}
	
	@Override
	public int allApps() throws DataAccessException {
		int app_count=sqlSession.selectOne("mapper.admin.appCount_all");
		return app_count;
	}

	@Override
	public int ukApps() throws DataAccessException {
		int app_count=sqlSession.selectOne("mapper.admin.appCount_uk");
		return app_count;
	}

	@Override
	public int ckApps() throws DataAccessException {
		int app_count=sqlSession.selectOne("mapper.admin.appCount_ck");
		return app_count;
	}

	@Override
	public int cfApps() throws DataAccessException {
		int app_count=sqlSession.selectOne("mapper.admin.appCount_cf");
		return app_count;
	}
	
	

	@Override
	public int appCount() throws DataAccessException {
		int app_count=sqlSession.selectOne("mapper.admin.appCount");
		return app_count;
	}

	@Override
	public void appManyHos_hostbl(String[] chk) throws DataAccessException {
	    sqlSession.update("mapper.admin.appHos_hosjointbl", chk);
	  
	}

	@Override
	public void appManyHos_rmtbl(String[] chk) throws DataAccessException {
		  sqlSession.update("mapper.admin.appHos_hosrmtbl", chk);
		
	}
	
	
	@Override
	// map에서 object로 설정시 integer와 string 타입을 변환 캐스팅만 하면 둘다 사용가능 , orderby가 string이기 때문에 object로 설정해줘야 한다. 중요! 
	public List<AdminVO> memberList(Map<String, Object> pagingMap) throws DataAccessException {
	    List<AdminVO> memberList = new ArrayList<AdminVO>();
	    int section = (int) pagingMap.get("section");
	    int pageNum = (int) pagingMap.get("pageNum");
	    Map<String, Object> pagingM = new HashMap<String, Object>();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    memberList = sqlSession.selectList("mapper.admin.memberList", pagingM);
	    return memberList;
	}

	@Override
	public List<AdminVO> enabledMemberList(Map<String, Object> pagingMap) throws DataAccessException {
	    List<AdminVO> memberList = new ArrayList<AdminVO>();
	    int section = (int) pagingMap.get("section");
	    int pageNum = (int) pagingMap.get("pageNum");
	    Map<String, Object> pagingM = new HashMap<String, Object>();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    memberList = sqlSession.selectList("mapper.admin.enabledMemberList", pagingM);
	    return memberList;
	}

	@Override
	public List<AdminVO> abledMemberList(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> memberList = new ArrayList<AdminVO>();
	    int section = (int) pagingMap.get("section");
	    int pageNum = (int) pagingMap.get("pageNum");
	    Map<String, Object> pagingM = new HashMap<String, Object>();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    memberList = sqlSession.selectList("mapper.admin.abledMemberList", pagingM);
		return memberList;
	}
	@Override
	public List<AdminVO> userMemberList(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> memberList = new ArrayList<AdminVO>();
	    int section = (int) pagingMap.get("section");
	    int pageNum = (int) pagingMap.get("pageNum");
	    Map<String, Object> pagingM = new HashMap<String, Object>();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    memberList = sqlSession.selectList("mapper.admin.userMemberList", pagingM);
		return memberList;
	}
	
	@Override
	public List<AdminVO> hosMemberList(Map<String, Object> pagingMap) throws DataAccessException {
		List<AdminVO> memberList = new ArrayList<AdminVO>();
	    int section = (int) pagingMap.get("section");
	    int pageNum = (int) pagingMap.get("pageNum");
	    Map<String, Object> pagingM = new HashMap<String, Object>();
	    pagingM.put("section", section);
	    pagingM.put("pageNum", pageNum);
	    memberList = sqlSession.selectList("mapper.admin.hosMemberList", pagingM);
		return memberList;
	}
	@Override
	public int allmember() throws DataAccessException {
		int count=sqlSession.selectOne("mapper.admin.allmember");
		return count;
	}
	@Override
	public int enabledmember() throws DataAccessException {
		int count=sqlSession.selectOne("mapper.admin.enabledmember");
		return count;
	}@Override
	public int abledmember() throws DataAccessException {
		int count=sqlSession.selectOne("mapper.admin.abledmember");
		return count;
	}@Override
	public int gmember() throws DataAccessException {
		int count=sqlSession.selectOne("mapper.admin.gmember");
		return count;
	}@Override
	public int hosmember() throws DataAccessException {
		int count=sqlSession.selectOne("mapper.admin.hosmember");
		return count;
	}




//=====================================================================
	public void checkHos(String hos_code) throws Exception {
		sqlSession.update("mapper.admin.checkHos_h", hos_code);
		sqlSession.update("mapper.admin.checkHos_a", hos_code);
		
	}

	@Override
	public AdminVO viewAppAdmin(String hos_code) throws Exception {
		
		return sqlSession.selectOne("mapper.admin.viewApplication",hos_code);
	}

	@Override
	public void approvalRM(String hos_code) throws Exception {
		sqlSession.update("mapper.admin.approvalHos",hos_code);
		sqlSession.update("mapper.admin.confirmRM",hos_code);

		
		
	}

	@Override
	public void rjRM(Map<String, String> rjMap) throws Exception {
		sqlSession.update("mapper.admin.rjRMHos",rjMap);
		sqlSession.update("mapper.admin.confirmRJ",rjMap);
		
		
	}
	
	
	
	
	
	
	
	



}
