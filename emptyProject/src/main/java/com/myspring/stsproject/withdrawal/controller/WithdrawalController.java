package com.myspring.stsproject.withdrawal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface WithdrawalController {

	
	ModelAndView updateEnabled(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
