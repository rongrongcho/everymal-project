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
	
    //리뷰 리스트 메소드(페이징)
	@Override
	public List selectAllRev(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException {
		 List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");
         Map map = new HashMap();
         //원래 여기 없던 부분인데 mappers에서 쿼리문에 변수 2개 이상 전달하기 위해서 HashMap map 만들어서 거기에 다 넣어줌. 그래서 변수 3개 보냄
         map.put("section", section);
         map.put("pageNum", pageNum);
         map.put("hos_code", hos_code);
         //
		 reviewList = sqlSession.selectList("mapper.hosReview.selectAllRevList", map);
		 
		return reviewList;
	}

    
	//리뷰 개수 구하기 메소드
	@Override
	public int reviewcount(String hos_code) throws DataAccessException {
		int reviewCount= sqlSession.selectOne("mapper.hosReview.reviewcount", hos_code);
		return reviewCount;
	}


	//리뷰 삭제요청 반영하기 메소드
	@Override
	public void requestDelRev(String[] items) throws DataAccessException {
		 String[]rv_code=items;
		 Map map=new HashMap();
		 map.put("array", rv_code);
		 sqlSession.update("mapper.hosReview.requestDelRev", map);
		
	}


	//리뷰 상세보기 메소드
	@Override
	public ReviewVO selectReview(String rv_code) throws DataAccessException {
		ReviewVO review=new ReviewVO();
		review=sqlSession.selectOne("mapper.hosReview.selectReview",rv_code);
		return review;
	}


	//리뷰 평점 구하기 메소드
	@Override
	public float reviewAvg(String hos_code) throws DataAccessException {
		float review_avg=0;
		review_avg=sqlSession.selectOne("mapper.hosReview.reviewAvg",hos_code);
		return review_avg;
	}

   //삭제요청된 리뷰 리스트 메소드 (관리자 페이지)
	@Override
	public List selectAllReqDelReviews(Map<String, Integer> pagingMap, int rv_delreq) throws DataAccessException {
		List<ReviewVO> relDelreviewList = new ArrayList<ReviewVO>();
		int section = pagingMap.get("section");
        int pageNum = pagingMap.get("pageNum");
        Map map = new HashMap();
        map.put("section", section);
        map.put("pageNum", pageNum);
        map.put("rv_delreq", rv_delreq);
        //
        relDelreviewList = sqlSession.selectList("mapper.hosReview.relDelreviewList", map);
		return relDelreviewList;
	}

    //삭제 요청된 리뷰 개수 구하기 메소드(관리자 페이지)
	@Override
	public int delReqreviewcount(int rv_delreq) throws DataAccessException {
		int reqDelreview_count=sqlSession.selectOne("mapper.hosReview.delReqreviewcount", rv_delreq);
		return reqDelreview_count;
	}

    //관리자가 리뷰 삭제하기 메소드
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
