package com.myspring.stsproject.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.board.vo.ArticleVO;

public interface BoardDAO {
	public List<ArticleVO> selectAllArticles() throws DataAccessException;
}
