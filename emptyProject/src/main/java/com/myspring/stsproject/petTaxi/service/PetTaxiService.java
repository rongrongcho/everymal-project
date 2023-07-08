package com.myspring.stsproject.petTaxi.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.petTaxi.vo.PetTaxiVO;

public interface PetTaxiService {
	
	public List<PetTaxiVO> listTaxi(Map<String, Object> paramMap) throws DataAccessException;
	public void taxiReserv(PetTaxiVO petTaxiVO) throws DataAccessException;

}
