package com.myspring.stsproject.hosReviewInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

public interface HosReviewDAO {

	 public List selectAllRev(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException;
     public int reviewcount(String hos_code)throws DataAccessException;     
     public void requestDelRev (String[] items)throws DataAccessException;
     public ReviewVO selectReview(String rv_code) throws DataAccessException;
     public float reviewAvg(String hos_code) throws DataAccessException;
     public List selectAllReqDelReviews(Map<String, Integer> pagingMap, int rv_delreq)throws DataAccessException;
     public int delReqreviewcount(int rv_delreq)throws DataAccessException;
     public void deleteAllReviews (String[] items)throws DataAccessException;
     public List selectAllRev2 (String hos_code)throws DataAccessException;
}
