package com.myspring.stsproject.hosReservation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosReservation.vo.ReservationVO;

public interface ReservationDAO {

	//public List<ReservationVO> selectPetinfo() throws DataAccessException;

	public List<ReservationVO> selectPetinfo(String user_code) throws DataAccessException;

	public void insertReservation(ReservationVO reservationVO) throws DataAccessException;

	public String resNumber() throws DataAccessException;
}