package com.myspring.stsproject.animal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.animal.dao.AnimalDAO;
import com.myspring.stsproject.animal.vo.AnimalVO;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService{

	@Autowired
	private AnimalDAO animalDAO;

	@Override
	public void addAnimal(AnimalVO animalVO) throws DataAccessException {
		animalDAO.addAnimal(animalVO);
	}

	@Override
	public String selectPetcode(AnimalVO animalVO) throws DataAccessException {
		String code = (String)animalDAO.selectPetcode(animalVO);
		return code;
	}
	
	
	
}
