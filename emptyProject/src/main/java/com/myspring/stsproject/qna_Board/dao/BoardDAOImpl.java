package com.myspring.stsproject.qna_Board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.qna_Board.vo.ArticleVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) throws DataAccessException {
		List<ArticleVO> articleList=new ArrayList<ArticleVO>();
		articleList = sqlSession.selectList("mapper.board.selectAllArticles", pagingMap);
		return articleList;
	}

	@Override
	public int selectToArticles() throws DataAccessException {
		return sqlSession.selectOne("selectToArticles");
	}

	@Override
	public int getNewArticleNo() {
		return sqlSession.selectOne("selectNewArticleNo");
	}
	
	@Override
	public void insertNewArticle(ArticleVO articleVO) throws DataAccessException {
		int q_code = getNewArticleNo();
		articleVO.setQ_code(q_code);
		sqlSession.insert("insertNewArticle", articleVO);
	}

	@Override
	public ArticleVO selectArticle(int q_code) throws DataAccessException {
		return sqlSession.selectOne("selectArticle", q_code);
	}

	@Override
	public void updateArticle(ArticleVO articleVO) throws DataAccessException {
		sqlSession.update("updateArticle", articleVO);
	}
	
	@Override
	public void deleteArticle(int q_code) throws DataAccessException {
		sqlSession.delete("deleteArticle", q_code);
	}

	@Override
	public void insertNewReply(ArticleVO articleVO) throws DataAccessException {
		sqlSession.update("insertNewReply", articleVO);
	}

	@Override
	public void deleteReply(int q_code) throws DataAccessException {
		sqlSession.delete("deleteReply", q_code);
	}

//	@Override
//	public List<ArticleVO> selectAllArticles() throws DataAccessException {
//		List<ArticleVO> articleList=sqlSession.selectList("mapper.board.selectAllArticles");
//		return articleList;
//	}
	
}
























