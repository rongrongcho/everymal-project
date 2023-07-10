package com.myspring.stsproject.board.service;

import java.util.List;

import com.myspring.stsproject.board.vo.ArticleVO;

public interface BoardService {
	public List<ArticleVO> listArticles() throws Exception;
}
