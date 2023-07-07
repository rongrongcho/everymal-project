package com.myspring.stsproject.hos_List.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.hos_List.dao.HosFilterDAO;
import com.myspring.stsproject.hos_List.vo.FilterVO;

@Service("hosFilterService")
public class HosFilterServiceImpl implements HosFilterService{
	
	@Autowired
	private HosFilterDAO hosFilterDAO;
	
	@Override
	public Map listHos(Map pagingMap) throws DataAccessException {
		Map articleMap=new HashMap<>();
		List<FilterVO> hosList=hosFilterDAO.selectAllHos(pagingMap);
		int totalHos=hosFilterDAO.selectTotHos(pagingMap);
		articleMap.put("hosList", hosList);
		articleMap.put("totalHos", totalHos);
		return articleMap;
	}

	@Override
	public Map revListHos(Map pagingMap) throws DataAccessException {
		Map articleMap=new HashMap<>();
		List<FilterVO> hosList=hosFilterDAO.selectRevAllHos(pagingMap);
		int totalHos=hosFilterDAO.selectTotHos(pagingMap);
		articleMap.put("hosList", hosList);
		articleMap.put("totalHos", totalHos);
		return articleMap;
	}

}
