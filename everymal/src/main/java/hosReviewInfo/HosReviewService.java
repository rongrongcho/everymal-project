package hosReviewInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qnaBoard.ArticleVO;

public class HosReviewService {
  HosReviewDAO hosReviewDAO;
	
  
  public HosReviewService() {
	  hosReviewDAO=new HosReviewDAO();
  }
  
	//hos_code 찾아오는 메소드
	public String findHosCode(String hos_id) {
		String hos_code=hosReviewDAO.findhos_code(hos_id);
		return hos_code;
	}
  
  
	//페이징쓰는 리뷰 리스트
		public Map<String, Integer> listRev(Map<String, Integer> pagingMap, String hos_code) {
			Map hosReviewMap=new HashMap<>();
			List<ArticleVO> hosReviewList=hosReviewDAO.selectAllRev(pagingMap, hos_code);
			int totalhosReview=hosReviewDAO.reviewcount(hos_code);
			hosReviewMap.put("hosReviewList", hosReviewList);
			hosReviewMap.put("totalhosReview", totalhosReview);
			return hosReviewMap;
		}
		
	
	
  //전체 리뷰 리스트 가져오는 메소드(병원정보 상세보기페이지)
	public List<ReviewVO> listRev2(String hos_code){
		List<ReviewVO> hosReviewList=hosReviewDAO.selectAllRev2(hos_code);
		return hosReviewList;
	}
	
	//리뷰 개수 가져오는 메소드
	public int reviewCount(String hos_code) {
		
		int review_count=hosReviewDAO.reviewcount(hos_code);
		return review_count;
	}
	
	
	//병원 평점 메소드
	public float reviewAvg(String hos_code) {
		float review_avg=hosReviewDAO.reviewAvg(hos_code);
		return review_avg;
	}
	
	
	//삭제요청 컬럼에 반영하기 메소드
	public void reqDelReview(String[] itmes) {
		hosReviewDAO.requestDelRev(itmes);
	}
	
	//상세보기
	public ReviewVO viewReview(String rv_code){
		ReviewVO review=null;
		review=hosReviewDAO.selectReview(rv_code);
		return review;
	}
	
	
	//삭제요청한 리뷰만 가져오기
	public List<ReviewVO> reqDelReviewList(){
		List<ReviewVO> reqDelhosReviewList=hosReviewDAO.selectAllreqDelReviews();
		return reqDelhosReviewList;
	}
	
	//페이징쓰는 삭제요청 리뷰 리스트
			public Map<String, Integer> allReqDel(Map<String, Integer> pagingMap, int rv_delreq) {
				Map hosRelDelMap=new HashMap<>();
				List<ArticleVO> hosReqDelList=hosReviewDAO.selectAllReqDelReviews(pagingMap, rv_delreq);	
				int totalhosReviewDelReq=hosReviewDAO.delReqreviewcount(rv_delreq);
				hosRelDelMap.put("hosReqDelList", hosReqDelList);	
				hosRelDelMap.put("totalhosReviewDelReq", totalhosReviewDelReq);	
				return hosRelDelMap;
			}
	
	
	
	//관리자가 리뷰 삭제하기
	public void deleteReveiws(String[] items) {
		hosReviewDAO.deleteAllReviews(items);
	}
	
}
