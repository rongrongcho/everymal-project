package com.myspring.stsproject.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface AdminController {
	public ModelAndView reqDelReviewList (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView reviewDelete (HttpServletRequest request, HttpServletResponse response) throws Exception;	
	public ModelAndView approvalHosM (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView checkApp (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView approval (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView rjRM (HttpServletRequest request, HttpServletResponse response) throws Exception;
	


}
