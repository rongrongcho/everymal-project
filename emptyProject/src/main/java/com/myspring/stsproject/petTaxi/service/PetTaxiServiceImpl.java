package com.myspring.stsproject.petTaxi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.petTaxi.dao.PetTaxiDAO;
import com.myspring.stsproject.petTaxi.vo.PetTaxiVO;
@Service("petTaxiService")

public class PetTaxiServiceImpl implements PetTaxiService{

	
	@Autowired
	private PetTaxiVO petTaxiVO;
	@Autowired
	private PetTaxiDAO petTaxiDAO;
	
	@Override
	public List<PetTaxiVO> listTaxi(Map<String, Object> paramMap) throws DataAccessException {
		List<PetTaxiVO> taxiList =new ArrayList<PetTaxiVO>();
		taxiList=petTaxiDAO.taxiList(paramMap);
		return taxiList;
	}

	@Override
	public void taxiReserv(PetTaxiVO petTaxiVO) throws DataAccessException {
		petTaxiDAO.taxiReserv(petTaxiVO);
		
	}
	


	
}
