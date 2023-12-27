package com.myspring.stsproject.withdrawal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("withdrawalDAO")
public class WithdrawalDAOImpl implements WithdrawalDAO {

	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public void updateEnabled(String id,String code) throws DataAccessException {
		if(code!=null&&code.startsWith("use")) {
			sqlSession.selectOne("mapper.withdrawal.selectGmember", id);
			sqlSession.update("mapper.withdrawal.updateGmember", id);
		}else {
			 sqlSession.selectOne("mapper.withdrawal.selectHosmember", id);
			 sqlSession.update("mapper.withdrawal.updateHosmember", id);
		}
	}

	
}

