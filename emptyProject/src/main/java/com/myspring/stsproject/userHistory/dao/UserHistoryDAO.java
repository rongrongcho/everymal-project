package com.myspring.stsproject.userHistory.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.userHistory.vo.UserHistoryVO;

public interface UserHistoryDAO {
	public String findhos_code(String hos_name) throws DataAccessException;
	
	public List<UserHistoryVO> hosResList(UserHistoryVO userHistoryVO) throws DataAccessException;
	public List<UserHistoryVO> hosRecent(UserHistoryVO userHistoryVO) throws DataAccessException;
	public UserHistoryVO selectHis(String _res_code) throws DataAccessException;
	public List<UserHistoryVO> txResList(UserHistoryVO userHistoryVO) throws DataAccessException;
	public List<UserHistoryVO> txRecent(UserHistoryVO userHistoryVO) throws DataAccessException;
	public UserHistoryVO selectTaxiHis(String tx_res_code) throws DataAccessException;
	
}
