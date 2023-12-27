package com.myspring.stsproject.userMypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.userMypage.vo.PetInfoVO;

@Repository("petInfoDAO")
public class PetInfoDAOImpl implements PetInfoDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<PetInfoVO> selectPetList(String user_code) throws DataAccessException {
		List<PetInfoVO> petList =sqlSession.selectList("mapper.petInfo.selectPetList", user_code);
		return petList;
	}

	@Override
	public void updatePet(PetInfoVO petInfoVO) throws DataAccessException {
		sqlSession.update("mapper.petInfo.updatePet", petInfoVO);
		
	}

	@Override
	public void removePetInfo(String pet_code) throws DataAccessException {
		sqlSession.delete("mapper.petInfo.removePetInfo", pet_code);
		
	}

}
