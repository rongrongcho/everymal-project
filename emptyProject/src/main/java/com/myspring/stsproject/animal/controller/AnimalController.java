package com.myspring.stsproject.animal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.myspring.stsproject.animal.vo.AnimalVO;

public interface AnimalController {
	public void addAnimal(@ModelAttribute("animalVO") AnimalVO animalVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
