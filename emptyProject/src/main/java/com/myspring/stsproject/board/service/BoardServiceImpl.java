package com.myspring.stsproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.board.dao.BoardDAO;
import com.myspring.stsproject.board.vo.ArticleVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	@Override
	public List<ArticleVO> listArticles() throws Exception {
		List<ArticleVO> articleList=boardDAO.selectAllArticles();
		return articleList;
	}
	
}
