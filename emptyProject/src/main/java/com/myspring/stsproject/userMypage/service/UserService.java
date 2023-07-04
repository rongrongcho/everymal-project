package com.myspring.stsproject.userMypage.service;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userMypage.vo.UserVO;

public interface UserService {
	public UserVO calluserInfo(String user_id) throws DataAccessException;
}
