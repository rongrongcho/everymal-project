package com.myspring.stsproject.hosReservation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosReservation.vo.ReservationVO;

@Repository("reservationDAO")
public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ReservationVO> selectPetinfo(String user_code) throws DataAccessException{
		List<ReservationVO> petInfoList=sqlSession.selectList("mapper.reservation.selectPetinfo",user_code);
		return petInfoList;
	}
	
	@Override
	public void insertReservation(ReservationVO reservationVO) throws DataAccessException{
		sqlSession.insert("mapper.reservation.insertReservation",reservationVO);
	}

	@Override
	public String resNumber() throws DataAccessException {
		int code=sqlSession.selectOne("mapper.reservation.resNumber");
		int maxNum=code+1;
		String res_code=String.format("res%04d", maxNum);
		return res_code;
	}
}	


