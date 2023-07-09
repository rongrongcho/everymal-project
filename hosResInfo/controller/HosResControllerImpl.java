package com.myspring.stsproject.hosResInfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.hosResInfo.service.HosResService;
import com.myspring.stsproject.hosResInfo.vo.ResVO;
import com.myspring.stsproject.hosmypage.service.HosMypageInfoService;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

@Controller("hosresController")
public class HosResControllerImpl implements HosResController {

	@Autowired
	HosResService hosResService;
	@Autowired
	ResVO resVO;
	@Autowired
	HosMypageInfoService hosmypageinfoService;
	@Autowired
	HosMypageInfoVO hosmypageinfoVO;
	
	
	@Override
	@RequestMapping(value = "/hos_ResInfo/hosResList.do", method = RequestMethod.GET)
	public ModelAndView hosResList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		request.setCharacterEncoding("utf-8");
		
	    response.setContentType("text/html;charset=utf-8");
	    HttpSession session=request.getSession();
	    String hos_id=(String)session.getAttribute("log_id");
		String hos_code=(String)session.getAttribute("hos_code");
		 resVO.setHos_code(hos_code);
		hosmypageinfoVO=hosmypageinfoService.selecthosInfo(hos_id);
		model.addAttribute("hosmypageinfoVO", hosmypageinfoVO);
		 String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section==null)?"1":_section);
			int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			Map hosResMap=hosResService.listReservation(pagingMap, hos_code);
			hosResMap.put("section", section);
			hosResMap.put("pageNum", pageNum);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("hosResMap", hosResMap);			
			mav.addObject("hosMypageInfoVO", hosmypageinfoVO);
			int res_count=hosResService.resCount(hos_code);
			model.addAttribute("res_count", res_count);
			
			
		return mav;
	}


	@Override
	@RequestMapping(value = "/hos_ResInfo/hosResForm.do", method = RequestMethod.GET)
	public ModelAndView hosResForm(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String hos_id=(String)session.getAttribute("log_id");
		hosmypageinfoVO=hosmypageinfoService.selecthosInfo(hos_id);
		model.addAttribute("hosmypageinfoVO", hosmypageinfoVO);
		ModelAndView mav = new ModelAndView(viewName);
		String res_code=request.getParameter("res_code");
		resVO=hosResService.viewReservation(res_code);
		 model.addAttribute("hosres", resVO); 
		return mav;
	}
}
