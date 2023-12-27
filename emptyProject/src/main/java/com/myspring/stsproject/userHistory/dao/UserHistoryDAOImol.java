package com.myspring.stsproject.userHistory.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.userHistory.vo.UserHistoryVO;


@Repository("userHistoryDAO")

public class UserHistoryDAOImol implements UserHistoryDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String findhos_code(String hos_name) throws DataAccessException {
		String hos_code=sqlSession.selectOne("mapper.history.findHos_code",hos_name);
		return hos_code;
	}

	@Override
	public List<UserHistoryVO> hosResList(UserHistoryVO userHistoryVO) throws DataAccessException {
		List<UserHistoryVO> hrList=new ArrayList<UserHistoryVO>();
		String user_id=userHistoryVO.getUser_id();
		hrList=sqlSession.selectList("mapper.history.userHosReservList",user_id);
		return hrList;
	}

	@Override
	public List<UserHistoryVO> hosRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
		List<UserHistoryVO> hrList=new ArrayList<UserHistoryVO>();
		String user_id=userHistoryVO.getUser_id();
		hrList=sqlSession.selectList("mapper.history.userHosRecentList",user_id);
		return hrList;		
	}

	@Override
	public UserHistoryVO selectHis(String res_code) throws DataAccessException {
		UserHistoryVO userHistoryVO=new UserHistoryVO();
		userHistoryVO=sqlSession.selectOne("mapper.history.selectHis",res_code);
		return userHistoryVO;
	}

	@Override
	public List<UserHistoryVO> txResList(UserHistoryVO userHistoryVO) throws DataAccessException {
	    List<UserHistoryVO> hrList=new ArrayList<UserHistoryVO>();
	    String user_code=userHistoryVO.getUser_code();
	    hrList=sqlSession.selectList("mapper.history.userTxReservList",user_code);
		return hrList;
	}

	@Override
	public List<UserHistoryVO> txRecent(UserHistoryVO userHistoryVO) throws DataAccessException {
	    List<UserHistoryVO> hrList=new ArrayList<UserHistoryVO>();
	    String user_code=userHistoryVO.getUser_code();
	    hrList=sqlSession.selectList("mapper.history.userTxRecentList",user_code);
		return hrList;
	}

	@Override
	public UserHistoryVO selectTaxiHis(String tx_res_code) throws DataAccessException {
		UserHistoryVO userHistoryVO=new UserHistoryVO();
		userHistoryVO=sqlSession.selectOne("mapper.history.selectTxHis",tx_res_code);
		return userHistoryVO;
	}

	

}
