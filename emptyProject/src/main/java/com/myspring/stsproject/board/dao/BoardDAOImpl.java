package com.myspring.stsproject.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.board.vo.ArticleVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ArticleVO> selectAllArticles() throws DataAccessException {
		List<ArticleVO> articleList=sqlSession.selectList("mapper.board.selectAllArticles");
		return articleList;
	}
	
}
























