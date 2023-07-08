package com.myspring.stsproject.petTaxi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.petTaxi.service.PetTaxiService;
import com.myspring.stsproject.petTaxi.vo.PetTaxiVO;

@Repository("petTaxiDAO")
public class PetTaxiDAOImpl implements PetTaxiDAO{
	@Autowired
	private PetTaxiVO petTaxiVO;
	@Autowired
	private PetTaxiService petTaxiService;
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<PetTaxiVO> taxiList(Map<String, Object> paramMap) throws DataAccessException {
		List<PetTaxiVO> taxiList =new ArrayList<PetTaxiVO>();
		taxiList=sqlSession.selectList("mapper.petTaxi.taxiList",paramMap);
		return taxiList;
	}
	@Override
	public void taxiReserv(PetTaxiVO petTaxiVO) throws DataAccessException {
		sqlSession.insert("mapper.petTaxi.taxiReserv",petTaxiVO);
		
	}
	

}
