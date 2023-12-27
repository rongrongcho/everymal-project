package com.myspring.stsproject.userMypage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.userMypage.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public UserVO selectUserInfo(String user_id) throws DataAccessException {
		UserVO userInfo=sqlSession.selectOne("mapper.userMypage.selectUserInfo", user_id);
		return userInfo;
	}


	@Override
	public boolean isValid(UserVO userVO) throws DataAccessException {
		Boolean result=sqlSession.selectOne("mapper.userMypage.isValid", userVO);
		System.out.println(result);
		return result != null ? result.booleanValue() : false;
	}


	@Override
	public String searchProfil(String user_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.userMypage.searchProfil", user_id);
	
	}


	@Override
	public List selectAllReview(Map<String, Integer> pagingMap, String user_id) throws DataAccessException {
		 List<UserVO> reviewList = new ArrayList();
		    int section = pagingMap.get("section");
		    int pageNum = pagingMap.get("pageNum");
		    Map map = new HashMap();
	         map.put("section", section);
	         map.put("pageNum", pageNum);
	         map.put("user_id", user_id);
	         reviewList = sqlSession.selectList("mapper.userMypage.selectAllReview", map);
			 
	 		return reviewList;
	}


	@Override
	public int reviewcount(String user_id) throws DataAccessException {
		int review_count =sqlSession.selectOne("mapper.userMypage.reviewcount", user_id);
		return review_count;
	}


	@Override
	public UserVO selectReview(String rv_code) throws DataAccessException {
		UserVO userVO=sqlSession.selectOne("mapper.userMypage.selectReview", rv_code);
		return userVO;
	}


	@Override
	public void updateUserMyreview(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.userMypage.updateUserMyreview", userVO);
		
	}


	@Override
	public void requestDelRev(String[] items) throws DataAccessException {
		 String[]rv_code=items;
		 Map map=new HashMap();
		 map.put("array", rv_code);
		 sqlSession.delete("mapper.userMypage.requestDelRev", map);
		
	}


	@Override
	public List selectAllQ(Map<String, Integer> pagingMap, String user_code) throws DataAccessException {
		List<UserVO> qList = new ArrayList<UserVO>();
	    int section = pagingMap.get("section");
	    int pageNum = pagingMap.get("pageNum");
	    Map map = new HashMap();       
        map.put("section", section);
        map.put("pageNum", pageNum);
        map.put("user_code", user_code);
        qList = sqlSession.selectList("mapper.userMypage.selectAllQ", map);            
        for (UserVO userVO : qList) {       
        	  if (userVO.getA_code() == null) {       		  
        	    userVO.setIsAnswer("�̴亯");        	    
        	  } else {
        	    userVO.setIsAnswer("�亯�Ϸ�");        	    
        	  }
        	}
		 
		return qList;
	}


	@Override
	public int qCount(String user_code) throws DataAccessException {
		int q_count =sqlSession.selectOne("mapper.userMypage.qCount", user_code);
		return q_count;
	}


	@Override
	public void requestDelQus(String[] items) throws DataAccessException {
		String[] qv_code=items;
		 Map map=new HashMap();
		 map.put("array", qv_code);
		 sqlSession.delete("mapper.userMypage.requestDelQus", map);
	}


	@Override
	public void updateUserInfo(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.userMypage.updateUserInfo", userVO);
		
	}

}
