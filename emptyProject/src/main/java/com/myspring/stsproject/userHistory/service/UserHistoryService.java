package com.myspring.stsproject.userHistory.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userHistory.vo.UserHistoryVO;

import com.myspring.stsproject.userHistory.vo.UserHistoryVO;

public interface UserHistoryService {
	
	
	//=====hosReserv
	public List<UserHistoryVO> listHReserv(UserHistoryVO userHistoryVO) throws DataAccessException;
	public List<UserHistoryVO> listHRecent(UserHistoryVO userHistoryVO) throws DataAccessException;
	public UserHistoryVO vHis(String res_code) throws DataAccessException;
	//======petTaxi
	public List<UserHistoryVO> listTxReserv(UserHistoryVO userHistoryVO) throws DataAccessException;
	public List<UserHistoryVO> listTxRecent(UserHistoryVO userHistoryVO) throws DataAccessException;
	public UserHistoryVO vTaxiHis(String tx_res_code) throws DataAccessException;
}
