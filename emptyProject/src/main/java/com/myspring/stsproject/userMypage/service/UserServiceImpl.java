package com.myspring.stsproject.userMypage.service;

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
	
}
