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
	      List<UserHistoryVO> historyList=userHistoryDAO.hosResList(userHistoryVO); //지난 이용 내역
	      return historyList;
	}

	@Override
	public List<UserHistoryVO> listHRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> recentReserv=userHistoryDAO.hosRecent(userHistoryVO); //최신 병원 예약 정보 
	      return recentReserv;
	}

	@Override
	public UserHistoryVO vHis(String res_code) throws DataAccessException {
	      //UserHistoryVO userHistoryVO=null;
	      userHistoryVO=userHistoryDAO.selectHis(res_code);
	      return userHistoryVO;
	}

	@Override
	public List<UserHistoryVO> listTxReserv(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> historyList=userHistoryDAO.txResList(userHistoryVO); //지난 택시 이용 내역
	      return historyList;
	}

	@Override
	public List<UserHistoryVO> listTxRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
	      List<UserHistoryVO> recentReserv=userHistoryDAO.txRecent(userHistoryVO); //최신 택시 예약 정보 
	      return recentReserv;
	}

	@Override
	public UserHistoryVO vTaxiHis(String tx_res_code) throws DataAccessException {
	      //HistoryVO historyVO=null;
	      userHistoryVO=userHistoryDAO.selectTaxiHis(tx_res_code);
	      return userHistoryVO;
	}

}
