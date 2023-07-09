package com.myspring.stsproject.animal.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.animal.vo.AnimalVO;

public interface AnimalDAO {
	public void addAnimal(AnimalVO animalVO) throws DataAccessException;
	public String selectPetcode(AnimalVO animalVO) throws DataAccessException;
}
