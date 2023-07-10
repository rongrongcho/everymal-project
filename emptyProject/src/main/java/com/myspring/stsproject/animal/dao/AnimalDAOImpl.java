package com.myspring.stsproject.animal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.animal.vo.AnimalVO;

@Repository("animalDAO")
public class AnimalDAOImpl implements AnimalDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addAnimal(AnimalVO animalVO) throws DataAccessException {
		sqlSession.insert("mapper.animal.addAnimal", animalVO);
	}

	@Override
	public String selectPetcode(AnimalVO animalVO) throws DataAccessException {
		String code = sqlSession.selectOne("mapper.animal.selectPetcode", animalVO);
		return code;
	}
	
	
	
}
