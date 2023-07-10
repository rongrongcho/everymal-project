package com.myspring.stsproject.petTaxi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface PetTaxiController {
	public ModelAndView openPetTaxiPage (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView goToTaxiSelect (HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ReservPetTaxi (HttpServletRequest request, HttpServletResponse response) throws Exception;
}
