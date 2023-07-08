package com.myspring.stsproject.hosList.dao;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosList.vo.hospitalVO;

public interface hospitalDAO {
	public HashMap<String,Object> hosInfo(String hos_code) throws DataAccessException;
	public HashMap<String,Object> hosJoin(String hos_code) throws DataAccessException;
	public String hos_tel(String hos_code) throws DataAccessException;
	public String getNewRevCode() throws DataAccessException;
	public void addReview(hospitalVO hospitalVO) throws DataAccessException;
	public String getResCode(hospitalVO hospitalVO) throws DataAccessException;
}
