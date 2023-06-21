package admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import userMypage.UserVO;

public class AdminService {
  AdminDAO adminDAO;
  AdminVO adminVO;
  
  
  public AdminService() {
	  adminDAO=new AdminDAO();
  }
  
  
  //=================등록 신청 페이지 
  
  
  public List<AdminVO> listRM(AdminVO adminVO){
	  
	  List<AdminVO> RM_List=adminDAO.selectRMList(adminVO);
	  return RM_List;
	  
  }
  
  
  
  
  
  public Map<String, Integer> listApps(Map<String, Integer> pagingMap) {
		Map reviewMap=new HashMap<>();
		List<AdminVO> appList=adminDAO.selectRMList(pagingMap);
		int totalApps=adminDAO.appCount();
		reviewMap.put("appList",appList);
		reviewMap.put("totalApps", totalApps);
		return reviewMap;
	}
   public int appCount() {
      
      int app_count= adminDAO.appCount();
      return app_count;
   }
	
  
  public void approvalHosM(String[] chk) {
	  adminDAO.appManyHos(chk);
  }
}
