package com.myspring.stsproject.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.board.service.BoardService;
import com.myspring.stsproject.board.vo.ArticleVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	private static String ART_IMAGE_REPO="C:\\developer\\stsproject\\image_repo";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		List articleList=boardService.listArticles();
		ModelAndView mav=new ModelAndView(viewName);
		if(articleList!=null) {
			System.out.println("있다");
			ArticleVO a=(ArticleVO) articleList.get(100);
			System.out.println(a.getTitle());
			System.out.println(a.getArticleNo());
			System.out.println(a.getId());
		}else {
			System.out.println("없다");
		}
		mav.addObject("aList", articleList);
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/articleForm.do", method = RequestMethod.GET)
	public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
}
