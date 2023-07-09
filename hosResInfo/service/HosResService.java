package com.myspring.stsproject.hosResInfo.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosResInfo.vo.ResVO;

public interface HosResService {
	public Map<String, Integer> listReservation(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException;
	public int resCount(String hos_code)throws DataAccessException;
	public ResVO viewReservation(String res_code)throws DataAccessException;


}
