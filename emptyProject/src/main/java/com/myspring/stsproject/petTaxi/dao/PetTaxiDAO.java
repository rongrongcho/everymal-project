package com.myspring.stsproject.petTaxi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.petTaxi.vo.PetTaxiVO;

public interface PetTaxiDAO {
	
	public List<PetTaxiVO> taxiList(Map<String, Object> paramMap) throws DataAccessException;
	public void taxiReserv(PetTaxiVO petTaxiVO) throws DataAccessException;
}
