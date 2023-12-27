package com.myspring.stsproject.qna_Board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.qna_Board.service.BoardService;
import com.myspring.stsproject.qna_Board.vo.ArticleVO;


@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;

	@Override
	@RequestMapping(value = "/qna_Board/qnaboardMain.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView qnaboardMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		
		String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section=Integer.parseInt((_section==null)?"1":_section);
		int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map articleMap=boardService.listArticles(pagingMap);
		articleMap.put("section", section);
		articleMap.put("pageNum", pageNum);
		
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("articleMap", articleMap);
		System.out.println(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/qnaboardWrite.do", method = RequestMethod.GET)
	public ModelAndView qnaboardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/addArticle.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addArticle(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		HttpSession session = request.getSession();
		articleVO.setUser_id((String)session.getAttribute("log_id"));
		if((boolean) session.getAttribute("isHos")) {
			articleVO.setUser_code((String)session.getAttribute("hos_code"));
		}else {
			articleVO.setUser_code((String)session.getAttribute("user_code"));
		}
		boardService.addArticle(articleVO);
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/viewArticle.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewArticle(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/qna_Board/qnaboard");
		articleVO = boardService.viewArticle(q_code);
		mav.addObject("article", articleVO);
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/modArticle.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modArticle(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		HttpSession session = request.getSession();
		articleVO.setUser_id((String)session.getAttribute("log_id"));
		boardService.modArticle(articleVO);
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/modReply.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modReply(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		HttpSession session = request.getSession();
		articleVO.setHos_id((String)session.getAttribute("log_id"));
		boardService.addReply(articleVO);
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/removeArticle.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeArticle(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		boardService.removeArticle(q_code);
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/removeReply.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeReply(@RequestParam("q_code") int q_code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		boardService.removeReply(q_code);
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/qna_Board/addReply.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addReply(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/qna_Board/qnaboardMain.do");
		
		HttpSession session = request.getSession();
		articleVO.setHos_name((String) session.getAttribute("hos_name"));
		articleVO.setHos_id((String)session.getAttribute("log_id"));
		boardService.addReply(articleVO);
		
		return mav;
	}
	
	
}
