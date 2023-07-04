package com.myspring.stsproject.hosReviewInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface HosReviewController {

	public ModelAndView hosReviewList (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView hosRevDel (HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView viewReview(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
