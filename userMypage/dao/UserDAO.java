package com.myspring.stsproject.userMypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userMypage.vo.UserVO;

public interface UserDAO {
	public UserVO selectUserInfo(String user_id) throws DataAccessException;
	public boolean isValid(UserVO userVO)throws DataAccessException;
	public String searchProfil(String user_id) throws DataAccessException;
	public List selectAllReview(Map<String, Integer> pagingMap, String user_id) throws DataAccessException;
	public int reviewcount(String user_id)throws DataAccessException;
	public UserVO selectReview(String rv_code) throws DataAccessException;
	public void updateUserMyreview(UserVO userVO)throws DataAccessException;
	public void requestDelRev(String[] items)throws DataAccessException;
	public List selectAllQ(Map<String, Integer> pagingMap, String user_code)throws DataAccessException;
	public int qCount(String user_code)throws DataAccessException;
	public void requestDelQus(String[] items)throws DataAccessException;
	public void updateUserInfo(UserVO userVO)throws DataAccessException;
}
