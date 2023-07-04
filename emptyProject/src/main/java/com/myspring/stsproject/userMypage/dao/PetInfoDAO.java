package com.myspring.stsproject.userMypage.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userMypage.vo.PetInfoVO;

public interface PetInfoDAO {
	public List<PetInfoVO> selectPetList (String user_code) throws DataAccessException;
	public void updatePet(PetInfoVO petInfoVO)throws DataAccessException;
	
}
