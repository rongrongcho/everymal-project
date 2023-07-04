package com.myspring.stsproject.userMypage.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userMypage.vo.UserVO;

public interface UserDAO {
	public UserVO selectUserInfo(String user_id) throws DataAccessException;
	public boolean isValid(UserVO userVO)throws DataAccessException;
}
