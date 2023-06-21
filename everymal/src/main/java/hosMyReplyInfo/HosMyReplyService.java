package hosMyReplyInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qnaBoard.ArticleVO;

public class HosMyReplyService {
  HosMyReplyDAO hosMyReplyDAO;
	
  
  public HosMyReplyService() {
	  hosMyReplyDAO=new HosMyReplyDAO();
  }
  
	
  
  
  //답변 리스트 가져오는 메소드
	public List<ReplyVO> listReply2(String hos_id){
		List<ReplyVO> hosMyReplyList=hosMyReplyDAO.selectAllReply(hos_id);
		return hosMyReplyList;
	}
	
	//페이징쓰는 리뷰 리스트
			public Map<String, Integer> listReply(Map<String, Integer> pagingMap, String hos_id) {
				Map hosReplyMap=new HashMap<>();
				List<ArticleVO> hosReplyList=hosMyReplyDAO.selectAllRev(pagingMap, hos_id);
				int totalhosReply=hosMyReplyDAO.replyCount(hos_id);
				hosReplyMap.put("hosReplyList", hosReplyList);
				hosReplyMap.put("totalhosReply", totalhosReply);
				return hosReplyMap;
			}
	
	
	//답변 개수 가져오는 메소드
	public int replyCount(String hos_id) {
		
		int reply_count=hosMyReplyDAO.replyCount(hos_id);
		return reply_count;
	}
	
	
	
	
	//내 답변 삭제하기
	public void delReply(String[] itmes) {
		hosMyReplyDAO.deleteReply(itmes);
	}
	
	//답변 상세보기
	public String viewReply(String a_code){
		
		String q_code=hosMyReplyDAO.selectReply(a_code);
		return q_code;
	}
	

}
