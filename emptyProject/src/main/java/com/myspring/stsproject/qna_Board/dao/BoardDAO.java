package com.myspring.stsproject.qna_Board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.qna_Board.vo.ArticleVO;


public interface BoardDAO {
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) throws DataAccessException;
	public int selectToArticles() throws DataAccessException;
	public int getNewArticleNo() throws DataAccessException;
	public void insertNewArticle(ArticleVO articleVO) throws DataAccessException;
	public ArticleVO selectArticle(int q_code) throws DataAccessException;
	public void updateArticle(ArticleVO articleVO) throws DataAccessException;
	public void insertNewReply(ArticleVO articleVO) throws DataAccessException;
	public void deleteArticle(int q_code) throws DataAccessException;
	public void deleteReply(int q_code) throws DataAccessException;
}
