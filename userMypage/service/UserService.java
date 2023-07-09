package com.myspring.stsproject.userMypage.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userMypage.vo.UserVO;

public interface UserService {
	public UserVO calluserInfo(String user_id) throws DataAccessException;
	public Map<String, Integer> listReviews(Map<String, Integer> pagingMap, String user_id) throws DataAccessException;
	public int reviewCount(String user_id) throws DataAccessException;
	public UserVO viewReview(String rv_code) throws DataAccessException;
	public void modUserMyReview(UserVO userVO) throws DataAccessException;
	public void reqDelReview(String[] items) throws DataAccessException;	
	public Map<String, Integer> listQuestions(Map<String, Integer> pagingMap, String user_code) throws DataAccessException;
	public int qCount(String user_code)  throws DataAccessException;
	public void reqDelQuestion(String[] items) throws DataAccessException;
	public String searchProfil(String user_id) throws DataAccessException;
	public void modUserInfo(UserVO userVO) throws DataAccessException;
}
