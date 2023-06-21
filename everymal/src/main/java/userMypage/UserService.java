
package userMypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
	//DAO에게 데이터베이스 데이터를 요구하는 역할
	UserDAO userDAO;
	UserVO userVO;
	
	
	public UserService() {
		userDAO=new UserDAO();

	}
	
	
	public UserVO calluserInfo(String user_id) {
		//controller 에 반환 
		return userDAO.selectUserInfo(user_id);
	}
	


	//회원 정보 수정 업데이트 
	
	public void modUserInfo(UserVO userVO) {
		userDAO.updateUserInfo(userVO);
		
	}
	
	
//=========================리뷰 관리 ===================================	
	//리뷰 목록 불러오기 
	

	
	public Map<String, Integer> listReviews(Map<String, Integer> pagingMap, String user_id) {
		Map reviewMap=new HashMap<>();
		List<UserVO> reviewList=userDAO.selectAllReview(pagingMap,user_id);
		int totalReviews=userDAO.reviewcount(user_id);
		reviewMap.put("reviewList", reviewList);
		reviewMap.put("totalReviews", totalReviews);
		return reviewMap;
	}
	
	
	

	
	
	
	//리뷰 개수 
	
	   //예약 개수 가져오는 메소드
   public int reviewCount(String user_id) {
      
      int review_count= userDAO.reviewcount(user_id);
      return review_count;
   }
   
   
   
   
   
   
   //선택 리뷰 삭제 요청 
   
   public void reqDelReview(String[] items) {
	   userDAO.requestDelRev(items);
	
}

   //viewReview 선택 리뷰 상세 보기 
   
	public UserVO viewReview(String rv_code)  {
		UserVO userVO=null;
		userVO=userDAO.selectReview(rv_code);
		return userVO;

	}
	
	
	
	public void modUserMyReview(UserVO userVO) {
		userDAO.updateUserMyreview(userVO);
		
	}
	
//=========================질문 관리 ===================================
	
	
//	public List<UserVO> listQ(String user_code){
//		List<UserVO> listQ=userDAO.selectAllQ(user_code);
//		return listQ;
//	}
//	
	
	
	public Map<String, Integer> listQuestions(Map<String, Integer> pagingMap, String user_code) {
		Map qMap=new HashMap<>();
		List<UserVO> qList=userDAO.selectAllQ(pagingMap,user_code);
		int totalQ=userDAO.qCount(user_code);
		qMap.put("qList", qList);
		qMap.put("totalQ",totalQ);
		return qMap;
	}
	
	
	
	
	
	//리뷰 개수 
	
	   //예약 개수 가져오는 메소드
   public int qCount(String user_code) {
      
      int q_count= userDAO.qCount(user_code);
      return q_count;
   }
   
   //선택 질문 삭제 
   public void reqDelQuestion(String[] items) {
	   userDAO.requestDelQus(items);
	
}
 
   
}
