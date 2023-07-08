package com.myspring.stsproject.hosList.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosList.vo.hospitalVO;

@Repository("hospitalDAO")
public class hospitalDAOImpl implements hospitalDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public HashMap<String,Object> hosInfo(String hos_code) throws DataAccessException {
		HashMap<String,Object> hosInfo = sqlSession.selectOne("mapper.hosDetail.hosInfo", hos_code);
		return hosInfo;
	}
	
	@Override
	public HashMap<String,Object> hosJoin(String hos_code) throws DataAccessException {
		HashMap<String,Object> hosJoin = sqlSession.selectOne("mapper.hosDetail.hosJoin", hos_code);
		return hosJoin;
	}

	@Override
	public String hos_tel(String hos_code) throws DataAccessException {
		String tel = sqlSession.selectOne("mapper.hosDetail.hos_tel", hos_code);
		return tel;
	}
}
