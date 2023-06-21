package admin;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.taglibs.standard.tag.compat.fmt.RequestEncodingTag;

import forImg.HosImgDAO;
import forImg.HosImgVO;
import forImg.HosMainImgCon;
import forImg.HosThumController;
import hosApp.HosAppDAO;
import hosApp.HosAppVO;
import hosMypageInfo.HosMypageInfoService;
import hosMypageInfo.HosMypageInfoVO;
import hosReviewInfo.HosReviewService;
import hosReviewInfo.ReviewVO;


@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	 HosReviewService hosReviewService;
	   ReviewVO reviewVO;
	   HosMypageInfoService hosmypageinfoService;
	   HosMypageInfoVO hosmypageinfoVO;
	   //============================================ 등록신청 페이지 
	   AdminVO adminVO;
	   AdminDAO adminDAO;
	   AdminService adminService;
	   //==============================================
		HosImgDAO hosImgDAO;
		HosImgVO hosImgVO;
		HosMainImgCon hosMainImgCon;
		HosThumController hosThumController;
		//===============================================
		HosAppDAO appDAO;
		HosAppVO appVO;
	   
       
	   public void init(ServletConfig config) throws ServletException {
	          hosReviewService=new HosReviewService();
	          reviewVO=new ReviewVO();
	      //=======================================================
	          hosmypageinfoService= new HosMypageInfoService();
	          hosmypageinfoVO=new HosMypageInfoVO();
	      //======================================================= 등록신청 페이지
	          adminVO = new AdminVO();
	          adminService=new AdminService();
	          adminDAO = new AdminDAO();
	      //=======================================================
	  		appDAO =new HosAppDAO();
			appVO=new HosAppVO();
	   
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
	      PrintWriter out = response.getWriter();
	      String nextPage=""; //원래는 "";
	      String action=request.getPathInfo();
	      System.out.println("요청이름 :" +action);
	      
	      try {
	    	  List<ReviewVO> reqDelhosReviewList=new ArrayList<ReviewVO>();
    		  List<AdminVO> rmList=new ArrayList<AdminVO>();// 신청등록 페이지 리스트 객체 생성 
	    	  if(action==null  || action.equals("/admin.do") ){

	    	  }else if(action.equals("/reqDelReviewList.do")) {
		    		// 관리자 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
	    		    int rv_delreq=1;
	        	    String _section=request.getParameter("section");
					String _pageNum=request.getParameter("pageNum");
					int section=Integer.parseInt((_section==null)?"1":_section);
					int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
					Map pagingMap=new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					Map reqDelhosReviewMap=hosReviewService.allReqDel(pagingMap, rv_delreq);
					reqDelhosReviewMap.put("section", section);
					reqDelhosReviewMap.put("pageNum", pageNum);
					 request.setAttribute("reqDelhosReviewMap", reqDelhosReviewMap);
	        	    //
	    		 // reqDelhosReviewList =hosReviewService.reqDelReviewList();
	    		 // request.setAttribute("reqDelhosReviewList", reqDelhosReviewList);
	    		  nextPage="/administrator/hosReview_reqDel_List.jsp";
	    	  }else if(action.equals("/reviewDelete.do")) {
	    		  response.setContentType("text/html;charset=UTF-8");
					String[] items=null;
	                items=request.getParameterValues("del_chk");
	        		if(items==null){
	        			out.print("<script>");
	        			out.print("alert('삭제할 리뷰를 선택해주세요!');");
	        			out.print("location.href='" +request.getContextPath()+ "/admin/reqDelReviewList.do" + "';");
	        			out.print("</script>");
	        			return;
	        		}else {
	        			
	        			for(int i=0; i<items.length; i++) {
	            			System.out.println( "items : " + items[i]);
	            		}
	        			hosReviewService.deleteReveiws(items); //여기 해야됨
	        			out.print("<script>");
	        			out.print("alert('리뷰 영구 삭제 성공!');");
	        			out.print("location.href='" +request.getContextPath() + "/admin/reqDelReviewList.do" + "';");
	        			out.print("</script>");
	        			return;
	        		}
	    	  }
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  else if(action.equals("/hosRM.do")) {
	    		  //신청등록 페이지 목록 조회 =========================================================================================

	    		    //rmList=adminService.listRM(adminVO);
				    String _section=request.getParameter("section");
		            String _pageNum=request.getParameter("pageNum");
		            int section=Integer.parseInt((_section==null)?"1":_section); 
		            int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
		            Map<String, Integer> pagingMap=new HashMap<String, Integer>(); 
		            pagingMap.put("section", section);
		            pagingMap.put("pageNum", pageNum);
		            Map adminMap=adminService.listApps(pagingMap);
		            adminMap.put("section", section);
		            adminMap.put("pageNum", pageNum);
		            request.setAttribute("adminMap", adminMap);
		            System.out.println("거기 있소?" + rmList);
			        int app_count=adminService.appCount();
		           request.setAttribute("app_count", app_count);
		           System.out.println("거기 있소?" + app_count);
	    		  	  
	    		 // request.setAttribute("rmList", rmList);
  
	    		  nextPage="/administrator/hopitalRegi.jsp";

	    	  }else if(action.equals("/approvalHosM.do")) {

	    		  String[] chk=request.getParameterValues("rm_chk");
	    		  System.out.println("rm_chk?" +chk);
	    		  if(chk==null) {
	        			out.print("<script>");
	        			out.print("alert('등록 승인 할 병원을 선택해주세요!');");
	        			out.print("location.href='" +request.getContextPath() + "/admin/hosRM.do" + "';");
	        			out.print("</script>");
	        			return;	  
	    		  }else {
	    			  for(int i=0; i<chk.length; i++) {
	    				  System.out.println("chk : " + chk[i]);
	    			  }
	    			  adminService.approvalHosM(chk);
	        			out.print("<script>");
	        			out.print("alert('병원 등록 성공!');");
	        			out.print("location.href='" +request.getContextPath() + "/admin/hosRM.do" + "';");
	        			out.print("</script>");
	        			return;
	    		  }
	    	  }
//	    	  else if(action.equals("/viewApp.do")) {
//
//	    		  	String hos_code=request.getParameter("hos_code");
//	    		  	appVO=appDAO.selectAppInfo(hos_code);
//		            request.setAttribute("appVO", appVO);
//		            nextPage="/administrator/adminApplication.jsp";
//	    		  
//	    	  }else if(action.equals("/rejectionRM.do")) {
//	    		  	String hos_code=request.getParameter("hos_code");  
//	    		  	System.out.println("야압"+hos_code);
//	    		  	String rm_say=request.getParameter("rm_say");
//	    		  	adminDAO.rejectionHos(rm_say,hos_code);
//		  			out.print("<script>");
//		  			out.print("alert('병원 등록 승인 거부!');");
//		  			out.print("location.href='" + request.getContextPath() + "/admin/viewApp.do" + "';");
//		  			
//		  			out.print("</script>");
//		  			return;
//	    		  
//	    	  }
	    	  
	    	  
	    	  RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
	          dispatcher.forward(request, response);  
	      }catch (Exception e) {
	          System.out.println("HosReviewController : 요청 처리 작업 중 오류 발생");
	          e.printStackTrace();
	       }
		
	}

}
