package com.myspring.stsproject.forImg.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.forImg.vo.HosImgVO;

public interface HosImgDAO {
	 //public HosImgVO getHosImg(String hos_code)throws DataAccessException;    
	 
	 public HosImgVO getHosPro(String hos_code)throws DataAccessException;    
	 
	 
	 
}
