package com.myspring.stsproject.userMypage.dao;

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

}
