package com.myspring.stsproject.hosResInfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.hosResInfo.dao.HosResDAO;
import com.myspring.stsproject.hosResInfo.vo.ResVO;

@Service("hosResService")
public class HosResServiceImpl implements HosResService{

	@Autowired
	HosResDAO hosResDAO;
	
	@Autowired
	ResVO resVO;
	
	@Override
	public Map<String, Integer> listReservation(Map<String, Integer> pagingMap, String hos_code)
			throws DataAccessException {
		Map hosResMap=new HashMap<>();
		List hosResList=hosResDAO.selectAllReservation(pagingMap, hos_code);
		int totalhosRes=hosResDAO.rescount(hos_code);
		hosResMap.put("hosResList", hosResList);
		hosResMap.put("totalhosRes", totalhosRes);
		return hosResMap;
	}

	@Override
	public int resCount(String hos_code) throws DataAccessException {
		int res_count=hosResDAO.rescount(hos_code);
		return res_count;
	}

	@Override
	public ResVO viewReservation(String res_code) throws DataAccessException {
		resVO=hosResDAO.selectRes(res_code);
		return resVO;
	}

}
