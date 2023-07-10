package com.myspring.stsproject.qna_Board.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.qna_Board.vo.ArticleVO;


public interface BoardService {
	public Map<String, Integer> listArticles(Map<String, Integer> pagingMap) throws Exception;
	public void addArticle(ArticleVO articleVO) throws DataAccessException;
	public ArticleVO viewArticle(int q_code) throws DataAccessException;
	public void modArticle(ArticleVO articleVO) throws DataAccessException;
	public void addReply(ArticleVO articleVO) throws DataAccessException;
	public void removeArticle(int q_code) throws DataAccessException;
	public void removeReply(int q_code) throws DataAccessException;
}
