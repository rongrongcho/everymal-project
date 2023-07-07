package com.myspring.stsproject.qna_Board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.qna_Board.dao.BoardDAO;
import com.myspring.stsproject.qna_Board.vo.ArticleVO;



@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;


	@Override
	public Map<String, Integer> listArticles(Map<String, Integer> pagingMap) throws Exception {
		Map articleMap=new HashMap<>();
		List<ArticleVO> articleList=boardDAO.selectAllArticles(pagingMap);
		int totalArticles=boardDAO.selectToArticles();
		articleMap.put("articleList", articleList);
		articleMap.put("totalArticles", totalArticles);
		return articleMap;
	}


	@Override
	public void addArticle(ArticleVO articleVO) throws DataAccessException {
		boardDAO.insertNewArticle(articleVO);
	}


	@Override
	public ArticleVO viewArticle(int q_code) throws DataAccessException {
		return boardDAO.selectArticle(q_code);
	}


	@Override
	public void modArticle(ArticleVO articleVO) throws DataAccessException {
		boardDAO.updateArticle(articleVO);
	}


	@Override
	public void addReply(ArticleVO articleVO) throws DataAccessException {
		boardDAO.insertNewReply(articleVO);
	}


	@Override
	public void removeArticle(int q_code) throws DataAccessException {
		boardDAO.deleteArticle(q_code);
	}


	@Override
	public void removeReply(int q_code) throws DataAccessException {
		boardDAO.deleteReply(q_code);
	}
	
}
