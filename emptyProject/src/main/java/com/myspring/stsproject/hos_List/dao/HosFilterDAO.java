package com.myspring.stsproject.hos_List.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface HosFilterDAO {
	public List selectAllHos(Map pagingMap) throws DataAccessException;
	public List selectRevAllHos(Map pagingMap) throws DataAccessException;
	public int selectTotHos(Map pagingMap) throws DataAccessException;
}
