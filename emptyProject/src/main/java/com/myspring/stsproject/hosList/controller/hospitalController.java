package com.myspring.stsproject.hosList.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface hospitalController {
	public ModelAndView Hospital_detail (HttpServletRequest request, HttpServletResponse response) throws Exception;
}
