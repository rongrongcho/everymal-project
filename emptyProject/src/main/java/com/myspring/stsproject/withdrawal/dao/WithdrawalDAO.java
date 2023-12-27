package com.myspring.stsproject.withdrawal.dao;

import org.springframework.dao.DataAccessException;

public interface WithdrawalDAO {


	public void updateEnabled(String id, String code) throws DataAccessException;

}