package com.myspring.stsproject.forImg.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.forImg.vo.HosImgVO;

@Repository("hosImgDAO")
public class HosImgDAOImpl implements HosImgDAO{

	@Autowired
	private HosImgVO hosImgVO;
	
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public HosImgVO getHosPro(String hos_code) throws DataAccessException {
		hosImgVO =sqlSession.selectOne("mapper.forImg.getHosPro", hos_code);
		return hosImgVO;
	}

}
