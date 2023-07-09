package com.myspring.stsproject.hosResInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.hosResInfo.vo.ResVO;

public interface HosResController {
	public ModelAndView hosResList (Model model,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView hosResForm(Model model,HttpServletRequest request, HttpServletResponse response)throws Exception;
}
