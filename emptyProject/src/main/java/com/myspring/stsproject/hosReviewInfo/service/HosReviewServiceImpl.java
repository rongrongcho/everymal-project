package com.myspring.stsproject.hosReviewInfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.qna_Board.vo.ArticleVO;
import com.myspring.stsproject.hosReviewInfo.dao.HosReviewDAO;
import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

@Service("hosReviewService")
public class HosReviewServiceImpl implements HosReviewService {

	@Autowired
	private HosReviewDAO hosReviewDAO;

	@Override
	public Map<String, Integer> listRev(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException {
		Map hosReviewMap=new HashMap();
		List<ArticleVO> hosReviewList=hosReviewDAO.selectAllRev(pagingMap, hos_code);
		int totalhosReview=hosReviewDAO.reviewcount(hos_code);
		hosReviewMap.put("hosReviewList", hosReviewList);
		hosReviewMap.put("totalhosReview", totalhosReview);
		return hosReviewMap;
		
	}

	@Override
	public void reqDelReview(String[] itmes) throws DataAccessException {
		hosReviewDAO.requestDelRev(itmes);
		
	}

	@Override
	public ReviewVO viewReview(String rv_code) throws DataAccessException {
		ReviewVO review=null;
		review=hosReviewDAO.selectReview(rv_code);
		return review;
	}

	@Override
	public float reviewAvg(String hos_code) throws DataAccessException {
		float review_avg=hosReviewDAO.reviewAvg(hos_code);
		return review_avg;
	}

	@Override
	public Map<String, Integer> allReqDel(Map<String, Integer> pagingMap, int rv_delreq) throws DataAccessException {
		Map hosRelDelMap=new HashMap();
		List<ArticleVO> hosReqDelList=hosReviewDAO.selectAllReqDelReviews(pagingMap, rv_delreq);	
		int totalhosReviewDelReq=hosReviewDAO.delReqreviewcount(rv_delreq);
		hosRelDelMap.put("hosReqDelList", hosReqDelList);	
		hosRelDelMap.put("totalhosReviewDelReq", totalhosReviewDelReq);	
		return hosRelDelMap;
	}

	@Override
	public void deleteReveiws(String[] items) throws DataAccessException {
		hosReviewDAO.deleteAllReviews(items);
		
	}

	@Override
	public List<ReviewVO> listRev2(String hos_code) throws DataAccessException {
		List<ReviewVO> hosReviewList=hosReviewDAO.selectAllRev2(hos_code);
		return hosReviewList;
		
	}
	
	

	
	
}
