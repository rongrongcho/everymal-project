package com.myspring.stsproject.hosList.service;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosList.vo.hospitalVO;

public interface hospitalService {
	public HashMap<String,Object> hosInfo(String hos_code) throws DataAccessException;
	public HashMap<String,Object> hosJoin(String hos_code) throws DataAccessException;
	public String hos_tel(String hos_code) throws DataAccessException;
}
