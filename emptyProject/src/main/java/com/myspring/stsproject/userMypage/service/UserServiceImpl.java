package com.myspring.stsproject.userMypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.hosReviewInfo.dao.HosReviewDAO;
import com.myspring.stsproject.userMypage.dao.UserDAO;
import com.myspring.stsproject.userMypage.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserVO calluserInfo(String user_id) throws DataAccessException {
		
		return userDAO.selectUserInfo(user_id);
	}

	@Override
	public String searchProfil(String user_id) throws DataAccessException {
		String user_imgName = userDAO.searchProfil(user_id);
	    if (user_imgName == null || user_imgName.isEmpty()) {
	       return "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\img\\blankProfil.png";
	    	
	    } else {
	        
	        return "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\imgRepo\\user_profil\\" + user_id+"\\"+user_imgName;
	      
	    }
	}

	@Override
	public Map<String, Integer> listReviews(Map<String, Integer> pagingMap, String user_id) throws DataAccessException {
		Map reviewMap=new HashMap();
		List<UserVO> reviewList=userDAO.selectAllReview(pagingMap,user_id);
		int totalReviews=userDAO.reviewcount(user_id);
		reviewMap.put("reviewList", reviewList);
		reviewMap.put("totalReviews", totalReviews);
		return reviewMap;
	}

	@Override
	public int reviewCount(String user_id) throws DataAccessException {
		 int review_count= userDAO.reviewcount(user_id);
	      return review_count;
	}

	@Override
	public UserVO viewReview(String rv_code) throws DataAccessException {
		UserVO userVO=userDAO.selectReview(rv_code);
		
		return userVO;
	}


	@Override
	public void modUserMyReview(UserVO userVO) throws DataAccessException {
		userDAO.updateUserMyreview(userVO);
		
	}

	
	@Override
	public void reqDelReview(String[] items) throws DataAccessException {
		userDAO.requestDelRev(items);
		
	}
	

	@Override
	public Map<String, Integer> listQuestions(Map<String, Integer> pagingMap, String user_code)
			throws DataAccessException {
		Map qMap=new HashMap<>();
		List<UserVO> qList=userDAO.selectAllQ(pagingMap,user_code);
		int totalQ=userDAO.qCount(user_code);
		qMap.put("qList", qList);
		qMap.put("totalQ",totalQ);
		return qMap;
		
	}

	@Override
	public int qCount(String user_code) throws DataAccessException {
		  int q_count= userDAO.qCount(user_code);
	      return q_count;
	}

	@Override
	public void reqDelQuestion(String[] items) throws DataAccessException {
		 userDAO.requestDelQus(items);
		
	}

	@Override
	public void modUserInfo(UserVO userVO) throws DataAccessException {
		userDAO.updateUserInfo(userVO);
		
	}
	
}
