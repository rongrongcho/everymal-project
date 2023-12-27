package com.myspring.stsproject.userHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.userHistory.dao.UserHistoryDAO;
import com.myspring.stsproject.userHistory.vo.UserHistoryVO;

@Service("userHistoryService")

public class UserHistoryServiceImpl implements UserHistoryService{
	
	@Autowired
	private  UserHistoryVO userHistoryVO;
	
	@Autowired
	private  UserHistoryDAO userHistoryDAO;

	@Override
	public List<UserHistoryVO> listHReserv(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> historyList=userHistoryDAO.hosResList(userHistoryVO); 
	      return historyList;
	}

	@Override
	public List<UserHistoryVO> listHRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> recentReserv=userHistoryDAO.hosRecent(userHistoryVO); 
	      return recentReserv;
	}

	@Override
	public UserHistoryVO vHis(String res_code) throws DataAccessException {
	      userHistoryVO=userHistoryDAO.selectHis(res_code);
	      return userHistoryVO;
	}

	@Override
	public List<UserHistoryVO> listTxReserv(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> historyList=userHistoryDAO.txResList(userHistoryVO); 
	      return historyList;
	}

	@Override
	public List<UserHistoryVO> listTxRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> recentReserv=userHistoryDAO.txRecent(userHistoryVO); 
	      return recentReserv;
	}

	@Override
	public UserHistoryVO vTaxiHis(String tx_res_code) throws DataAccessException {
	      userHistoryVO=userHistoryDAO.selectTaxiHis(tx_res_code);
	      return userHistoryVO;
	}

}
