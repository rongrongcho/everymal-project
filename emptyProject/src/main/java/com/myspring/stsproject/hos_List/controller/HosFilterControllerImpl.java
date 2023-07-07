package com.myspring.stsproject.hos_List.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.hos_List.service.HosFilterService;
import com.myspring.stsproject.hos_List.vo.FilterVO;
import com.myspring.stsproject.member.vo.MemberVO;

@Controller("hosFilterController")
public class HosFilterControllerImpl implements HosFilterController{

	@Autowired
	private HosFilterService hosFilterService;
	
	@Autowired
	private FilterVO filterVO;
	
	@Override
	@RequestMapping(value = "/hos_List/hos_filter.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView hos_filter(@ModelAttribute("filterVO") FilterVO filterVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
		String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section=Integer.parseInt((_section==null)?"1":_section);
		int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
		Map pagingMap=new HashMap<>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		pagingMap.put("filterVO", filterVO);
		
		boolean orderby=Boolean.parseBoolean(request.getParameter("orderby"));
		Map hosMap=new HashMap<>();
		System.out.println(orderby);
		if(orderby) {
			hosMap=hosFilterService.revListHos(pagingMap);
		}else {
			hosMap=hosFilterService.listHos(pagingMap);
		}
		hosMap.put("section", section);
		hosMap.put("pageNum", pageNum);
		hosMap.put("filterchk", filterVO);
		hosMap.put("orderby", orderby);
		request.setAttribute("hosMap", hosMap);
		
		return mav;
	}

}
