package com.myspring.stsproject.hosList.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.hosList.dao.hospitalDAO;
import com.myspring.stsproject.hosList.vo.hospitalVO;

@Service("hospitalService")
public class hospitalServiceImpl implements hospitalService{

	@Autowired hospitalDAO hospitalDAO;

	@Override
	public HashMap<String,Object> hosInfo(String hos_code) throws DataAccessException {
		return hospitalDAO.hosInfo(hos_code);
	}
	
	@Override
	public HashMap<String,Object> hosJoin(String hos_code) throws DataAccessException {
		return hospitalDAO.hosJoin(hos_code);
	}

	@Override
	public String hos_tel(String hos_code) throws DataAccessException {
		return hospitalDAO.hos_tel(hos_code);
	}
	
}
