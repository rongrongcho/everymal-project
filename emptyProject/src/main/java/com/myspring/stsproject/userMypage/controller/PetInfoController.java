package com.myspring.stsproject.userMypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.userMypage.vo.PetInfoVO;

public interface PetInfoController {
	public ModelAndView  myPetList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modPet (Model model,  HttpServletRequest request, HttpServletResponse response) throws Exception;
}
