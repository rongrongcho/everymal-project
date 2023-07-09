package com.myspring.stsproject.hosReviewInfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

public interface HosReviewService {
	public Map<String, Integer> listRev(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException;
	public void reqDelReview(String[] items) throws DataAccessException;
	public ReviewVO viewReview(String rv_code) throws DataAccessException;
	public float reviewAvg (String hos_code) throws DataAccessException;
	public Map<String, Integer> allReqDel(Map<String, Integer> pagingMap, int rv_delreq)throws DataAccessException;
	public void deleteReveiws (String[] items) throws DataAccessException;	
	public List<ReviewVO> listRev2 (String hos_code) throws DataAccessException;
	
}
