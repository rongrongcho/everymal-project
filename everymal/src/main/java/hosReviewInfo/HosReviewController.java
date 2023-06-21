package hosReviewInfo;

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

import forImg.HosImgDAO;
import forImg.HosImgVO;
import hosMypageInfo.HosMypageInfoService;
import hosMypageInfo.HosMypageInfoVO;


@WebServlet("/hosReview/*")
public class HosReviewController extends HttpServlet {
   HosReviewService hosReviewService;
   ReviewVO reviewVO;
   HosImgDAO hosImgDAO;
   HosImgVO hosImgVO;
   HosMypageInfoService hosmypageinfoService;
   HosMypageInfoVO hosmypageinfoVO;

   public void init(ServletConfig config) throws ServletException {
          hosReviewService=new HosReviewService();
          reviewVO=new ReviewVO();
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
      PrintWriter out = response.getWriter();
      String nextPage=""; //원래는 "";
      String action=request.getPathInfo();
      System.out.println("요청이름 :" +action);
      
      
      try {  
    	  List<ReviewVO> hosReviewList=new ArrayList<ReviewVO>();
         if(action==null  || action.equals("/hosReviewList.do") ){
        	    HttpSession session=request.getSession();
        	    //session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
		           request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
				//String hos_code=hosReviewService.findHosCode(hos_id);//hos_code 불러오는 메소드수행
				//===================이미지 
				String hos_code=(String)session.getAttribute("hos_code");
				hosImgVO=hosImgDAO.getHosPro(hos_code);
           		request.setAttribute("hosImgVO", hosImgVO);
				
				
				System.out.println("병원코드 불러왔나?>>>>"+ hos_code);
        	    System.out.println("병원아이디 불러왔나?>>>>>"+ hos_id);
        	   
        	    //병원정보 상세보기 페이지에서 리뷰 슬라이더에 전체 리뷰 불러오기
        	    hosReviewList = hosReviewService.listRev2(hos_code);
        	    int review_count=hosReviewService.reviewCount(hos_code);
        	    float review_avg_result=hosReviewService.reviewAvg(hos_code);
        	    String review_avg=String.format("%.2f", review_avg_result);
        	   
        	    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
        	    String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map hosReviewMap=hosReviewService.listRev(pagingMap, hos_code);
				hosReviewMap.put("section", section);
				hosReviewMap.put("pageNum", pageNum);
				 request.setAttribute("hosReviewMap", hosReviewMap);
        	    //
        	   
        	    request.setAttribute("hosReviewList", hosReviewList);
				request.setAttribute("review_count", review_count);
				request.setAttribute("review_avg", review_avg);
            nextPage="/hos_ReviewInfo/hosReviewList.jsp";
            }else if(action.equals("/hosRevDel.do")) {
            	response.setContentType("text/html;charset=UTF-8");
            	HttpSession session=request.getSession();
        	   // session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				String[] items=null;
                items=request.getParameterValues("del_chk");
        		if(items==null){
        			out.print("<script>");
        			out.print("alert('삭제요청 할 리뷰를 선택해주세요!');");
        			out.print("location.href='" +request.getContextPath()+ "/hosReview" + "';");
        			out.print("</script>");
        			return;
        		}else {
        			
        			for(int i=0; i<items.length; i++) {
            			System.out.println( "items : " + items[i]);
            		}
        			hosReviewService.reqDelReview(items);
        			out.print("<script>");
        			out.print("alert('삭제요청 성공!');");
        			out.print("location.href='" +request.getContextPath() + "/hosReview" + "';");
        			out.print("</script>");
        			return;
        		}
            	//nextPage="/hosReview";		
            }else if(action.equals("/viewReview.do")) {
            	HttpSession session=request.getSession();
        	   // session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
            	String rv_code=request.getParameter("rv_code");
				reviewVO=hosReviewService.viewReview(rv_code);
				 request.setAttribute("hosReview", reviewVO); 
				    nextPage="/hos_ReviewInfo/hosViewReview.jsp";	
            }
                    
      RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
      dispatcher.forward(request, response);
      } catch (Exception e) {
         System.out.println("HosReviewController : 요청 처리 작업 중 오류 발생");
         e.printStackTrace();
      }
      
   }
      
          
 	
 	
   
}