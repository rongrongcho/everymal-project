package com.myspring.stsproject.hos_List.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hos_List.vo.FilterVO;

@Repository("hosFilterDAO")
public class HosFilterDAOImpl implements HosFilterDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllHos(Map pagingMap) throws DataAccessException {
		List<FilterVO> hosList=new ArrayList<FilterVO>();
		int section=(int) pagingMap.get("section");
		int pageNum=(int) pagingMap.get("pageNum");
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		filter.setPageNum((section-1)*5+pageNum);
		filter.setSection((section-1)*5+pageNum+12);
		hosList = sqlSession.selectList("mapper.hosfilter.selectAllHos", filter);
		return hosList;
	}

	@Override
	public List selectRevAllHos(Map pagingMap) throws DataAccessException {
		List<FilterVO> hosList=new ArrayList<FilterVO>();
		int section=(int) pagingMap.get("section");
		int pageNum=(int) pagingMap.get("pageNum");
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		filter.setPageNum((section-1)*5+pageNum);
		filter.setSection((section-1)*5+pageNum+12);
		hosList = sqlSession.selectList("mapper.hosfilter.selectRevAllHos", filter);
		return hosList;
	}

	@Override
	public int selectTotHos(Map pagingMap) throws DataAccessException {
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		int totalCount=sqlSession.selectOne("mapper.hosfilter.selectTotHos", filter);
		return totalCount;
	}

}
