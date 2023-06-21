package hosMypageInfo;

public class HosMypageInfoService {
	HosMypageInfoDAO hosmypageinfoDAO;
	
	public HosMypageInfoService() {
		hosmypageinfoDAO=new HosMypageInfoDAO();
	}
	
	public HosMypageInfoVO callhosInfo(String hos_id) {
		//controller 에 반환 
		return hosmypageinfoDAO.selecthosInfo(hos_id);
	}	
	
}
