package hosResInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qnaBoard.ArticleVO;

public class HosResService {
  HosResDAO hosResDAO;
	
  
  public HosResService() {
	  hosResDAO=new HosResDAO();
  }
  
  //예약 리스트 가져오는 메소드
	public List<ResVO> listRes(String hos_code){
		List<ResVO> hosresList=hosResDAO.selectAllListRes(hos_code);
		return hosresList;
	}
	
	
	//페이징쓰는 예약 리스트
			public Map<String, Integer> listReservation(Map<String, Integer> pagingMap, String hos_code) {
				Map hosResMap=new HashMap<>();
				List<ArticleVO> hosResList=hosResDAO.selectAllReservation(pagingMap, hos_code);
				int totalhosRes=hosResDAO.rescount(hos_code);
				hosResMap.put("hosResList", hosResList);
				hosResMap.put("totalhosRes", totalhosRes);
				return hosResMap;
			}
				
	
	//예약 개수 가져오는 메소드
	public int resCount(String hos_code) {
		
		int res_count=hosResDAO.rescount(hos_code);
		return res_count;
	}
	
	//hos_code 찾아오는 메소드
	public String findHosCode(String hos_id) {
		String hos_code=hosResDAO.findhos_code(hos_id);
		return hos_code;
	}
	
	//예약정보 상세보기
	public ResVO viewReservation(String res_code) {
		ResVO resVO=null;
		resVO=hosResDAO.selectRes(res_code);
		return resVO;
	}
}
