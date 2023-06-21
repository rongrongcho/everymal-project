package hosResInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forImg.HosImgDAO;
import forImg.HosImgVO;
import hosMypageInfo.HosMypageInfoService;
import hosMypageInfo.HosMypageInfoVO;



@WebServlet("/hosres/*")
public class HosResController extends HttpServlet {
	HosResService hosResService;
	ResVO resVO;
    HosImgDAO hosImgDAO;
    HosImgVO hosImgVO;
    HosMypageInfoService hosmypageinfoService;
    HosMypageInfoVO hosmypageinfoVO;
	
	public void init(ServletConfig config) throws ServletException {
		hosResService= new HosResService();
		resVO= new ResVO();
        hosImgVO=new HosImgVO();
        hosImgDAO=new HosImgDAO();
        hosmypageinfoService= new HosMypageInfoService();
        hosmypageinfoVO=new HosMypageInfoVO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
    
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage="";  
		String action=request.getPathInfo();
		System.out.println("요청이름 : " +action);
		
		try {
			List<ResVO> hosresList=new ArrayList<ResVO>();
			if(action == null || action.equals("/hosResList.do")){
				HttpSession session=request.getSession();
				//session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				String hos_code=hosResService.findHosCode(hos_id);
				resVO.setHos_code(hos_code);
				
				hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
		        request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
				
				//===================이미지 
				hosImgVO=hosImgDAO.getHosPro(hos_code);
           		request.setAttribute("hosImgVO", hosImgVO);
				
				 String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map hosResMap=hosResService.listReservation(pagingMap, hos_code);
				hosResMap.put("section", section);
				hosResMap.put("pageNum", pageNum);
				 request.setAttribute("hosResMap", hosResMap);
				
				
				//hosresList=hosResService.listRes(hos_code);
			    int res_count=hosResService.resCount(hos_code);
				request.setAttribute("hosresList", hosresList);
			    
				request.setAttribute("res_count", res_count);
				nextPage="/hos_ResInfo/hosResList.jsp";
			}
			else if(action.equals("/hosResForm.do")) {
				HttpSession session=request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
		        request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
				
				//===================이미지 
				String hos_code=(String)session.getAttribute("hos_code");
				hosImgVO=hosImgDAO.getHosPro(hos_code);
           		request.setAttribute("hosImgVO", hosImgVO);
           		
           		
           		//=====================================
				
				String res_code=request.getParameter("res_code");
				resVO=hosResService.viewReservation(res_code);
				 request.setAttribute("hosres", resVO); 
				    nextPage="/hos_ResInfo/hosResForm.jsp";
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		}catch (Exception e) {
			System.out.println("요청처리 중 에러!!");
			e.printStackTrace();
		}
		
	}
}
