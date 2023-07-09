package com.myspring.stsproject.animal.service;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.animal.vo.AnimalVO;

public interface AnimalService {
	public void addAnimal(AnimalVO animalVO) throws DataAccessException;
	public String selectPetcode(AnimalVO animalVO) throws DataAccessException;
}
