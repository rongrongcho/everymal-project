package admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberlistService {
  MemberlistDAO memberlistDAO;
	
  
  public MemberlistService() {
	  memberlistDAO=new MemberlistDAO();
  }
  
	//페이징쓰는 리뷰 리스트(기본) 
		public Map<String, Integer> list(Map<String, Integer> pagingMap) {
			Map memberlistMap=new HashMap<>();
			List<MemberlistVO> memberList=memberlistDAO.selectAllList(pagingMap);
			int totalList=memberlistDAO.membercount();
			memberlistMap.put("memberList", memberList);
			memberlistMap.put("totalList", totalList);
			return memberlistMap;
		}
	
		//페이징쓰는 리뷰 리스트(일반회원) 
		public Map<String, Object> Glist(Map<String, Object> pagingMap) {
			Map memberlistMap=new HashMap<>();
			List<MemberlistVO> memberList=memberlistDAO.selectAllGmember(pagingMap);
			int totalList=memberlistDAO.gmembercount();
			memberlistMap.put("memberList", memberList);
			memberlistMap.put("totalList", totalList);
			return memberlistMap;
		}
		
		//페이징쓰는 리뷰 리스트(병원회원) 
		public Map<String, Object> Hlist(Map<String, Object> pagingMap) {
			Map memberlistMap=new HashMap<>();
			List<MemberlistVO> memberList=memberlistDAO.selectAllHosmember(pagingMap);
			int totalList=memberlistDAO.hosmembercount();
			memberlistMap.put("memberList", memberList);
			memberlistMap.put("totalList", totalList);
			return memberlistMap;
		}
		
		//페이징쓰는 리뷰 리스트(탈퇴회원) 
		public Map<String, Integer> Elist(Map<String, Integer> pagingMap) {
			Map memberlistMap=new HashMap<>();
			List<MemberlistVO> memberList=memberlistDAO.selectAllEnabled(pagingMap);
			int totalList=memberlistDAO.membercount();
			memberlistMap.put("memberList", memberList);
			memberlistMap.put("totalList", totalList);
			return memberlistMap;
		}
		
		//페이징쓰는 리뷰 리스트(활동회원) 
		public Map<String, Integer> Alist(Map<String, Integer> pagingMap) {
			Map memberlistMap=new HashMap<>();
			List<MemberlistVO> memberList=memberlistDAO.selectAllAbled(pagingMap);
			int totalList=memberlistDAO.membercount();
			memberlistMap.put("memberList", memberList);
			memberlistMap.put("totalList", totalList);
			return memberlistMap;
		}
		
	//멤버 수 가져오는 메소드
	public int memberCount() {
		int member_count=memberlistDAO.membercount();
		return member_count;
	}
	
	//멤버 수 가져오는 메소드
	public int gmemberCount() {
		int member_count=memberlistDAO.gmembercount();
		return member_count;
	}
	
	//멤버 수 가져오는 메소드
	public int hosmemberCount() {
		int member_count=memberlistDAO.hosmembercount();
		return member_count;
	}
	
	
}
