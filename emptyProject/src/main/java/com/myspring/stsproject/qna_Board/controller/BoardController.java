package com.myspring.stsproject.qna_Board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.qna_Board.vo.ArticleVO;

public interface BoardController {
	public ModelAndView qnaboardMain(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView qnaboardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addArticle(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewArticle(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modArticle(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modReply(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeArticle(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeReply(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addReply(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
