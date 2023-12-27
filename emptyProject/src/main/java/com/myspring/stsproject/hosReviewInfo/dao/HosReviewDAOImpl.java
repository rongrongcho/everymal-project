package com.myspring.stsproject.hosReviewInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

@Repository("hosReviewDAO")
public class HosReviewDAOImpl implements HosReviewDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllRev(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException {
		 List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");
         Map map = new HashMap();
      
         map.put("section", section);
         map.put("pageNum", pageNum);
         map.put("hos_code", hos_code);
         
		 reviewList = sqlSession.selectList("mapper.hosReview.selectAllRevList", map);
		 
		return reviewList;
	}

    

	@Override
	public int reviewcount(String hos_code) throws DataAccessException {
		int reviewCount= sqlSession.selectOne("mapper.hosReview.reviewcount", hos_code);
		return reviewCount;
	}


	@Override
	public void requestDelRev(String[] items) throws DataAccessException {
		 String[]rv_code=items;
		 Map map=new HashMap();
		 map.put("array", rv_code);
		 sqlSession.update("mapper.hosReview.requestDelRev", map);
		
	}



	@Override
	public ReviewVO selectReview(String rv_code) throws DataAccessException {
		ReviewVO review=new ReviewVO();
		review=sqlSession.selectOne("mapper.hosReview.selectReview",rv_code);
		return review;
	}



//	@Override
//	public float reviewAvg(String hos_code) throws DataAccessException {
//		float review_avg=0;
//		review_avg=sqlSession.selectOne("mapper.hosReview.reviewAvg",hos_code);
//		return review_avg;
//	}
	
	
	@Override
	public float reviewAvg(String hos_code) throws DataAccessException {
	    // review_avg 변수를 0.0으로 초기화 (기본값 설정)
	    Float review_avg = 0.0f;
	    review_avg = sqlSession.selectOne("mapper.hosReview.reviewAvg", hos_code);
	    // floatValue()를 호출하기 전에 review_avg가 null인지 확인 
	    if (review_avg != null) {
	        return review_avg.floatValue();
	    } else {
	        return 0.0f; // null 값에 대한 기본값으로 0.0f를 반환
	    }
	}

	

	@Override
	public List selectAllReqDelReviews(Map<String, Integer> pagingMap, int rv_delreq) throws DataAccessException {
		List<ReviewVO> relDelreviewList = new ArrayList<ReviewVO>();
		int section = pagingMap.get("section");
        int pageNum = pagingMap.get("pageNum");
        Map map = new HashMap();
        map.put("section", section);
        map.put("pageNum", pageNum);
        map.put("rv_delreq", rv_delreq);
        
        relDelreviewList = sqlSession.selectList("mapper.hosReview.relDelreviewList", map);
		return relDelreviewList;
	}

  
	@Override
	public int delReqreviewcount(int rv_delreq) throws DataAccessException {
		int reqDelreview_count=sqlSession.selectOne("mapper.hosReview.delReqreviewcount", rv_delreq);
		return reqDelreview_count;
	}

	@Override
	public void deleteAllReviews(String[] items) throws DataAccessException {
		 String[]rv_code=items;
		 Map map=new HashMap();
		 map.put("array", rv_code);
		 sqlSession.update("mapper.hosReview.deleteAllReviews", map);
		
	}


	@Override
	public List selectAllRev2(String hos_code) throws DataAccessException {
		List<ReviewVO> hosReviewList= sqlSession.selectList("mapper.hosReview.selectAllRev2", hos_code);
		return hosReviewList;
	}
	
	
}
