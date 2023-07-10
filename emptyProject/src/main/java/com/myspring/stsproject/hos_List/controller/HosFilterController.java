package com.myspring.stsproject.hos_List.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.hos_List.vo.FilterVO;

public interface HosFilterController {
	public ModelAndView hos_filter(@ModelAttribute("filterVO") FilterVO filterVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
