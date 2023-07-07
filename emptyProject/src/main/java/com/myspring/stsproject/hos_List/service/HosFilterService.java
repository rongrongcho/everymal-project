package com.myspring.stsproject.hos_List.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface HosFilterService {
	public Map listHos(Map pagingMap) throws DataAccessException;
	public Map revListHos(Map pagingMap) throws DataAccessException;
}
