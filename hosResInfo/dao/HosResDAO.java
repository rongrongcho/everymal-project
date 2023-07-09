package com.myspring.stsproject.hosResInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosResInfo.vo.ResVO;

public interface HosResDAO {
	public List selectAllReservation(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException;
	public int rescount(String hos_code)throws DataAccessException;
	public ResVO selectRes(String res_code)throws DataAccessException;
}
