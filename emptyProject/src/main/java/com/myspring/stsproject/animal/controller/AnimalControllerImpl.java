package com.myspring.stsproject.animal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.stsproject.animal.service.AnimalService;
import com.myspring.stsproject.animal.vo.AnimalVO;

@Controller("animalController") 
public class AnimalControllerImpl implements AnimalController{
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private AnimalVO animalVO;

	@Override
	@RequestMapping(value = "/animal/addAnimal.do", method = RequestMethod.POST)
	public void addAnimal(@ModelAttribute("animalVO") AnimalVO animalVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = animalService.selectPetcode(animalVO);
        int maxNum = Integer.parseInt(code) + 1;
        code = String.format("pet%04d", maxNum);
        animalVO.setPet_code(code);
        animalService.addAnimal(animalVO);
	}
}
