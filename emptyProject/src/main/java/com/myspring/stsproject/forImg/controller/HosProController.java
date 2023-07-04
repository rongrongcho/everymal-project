package com.myspring.stsproject.forImg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface HosProController {
   public void hosProImgDown (@RequestParam String hos_pro, @RequestParam String hos_code, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
