package hosMyReplyInfo;

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


@WebServlet("/hosReply/*")
public class HosMyReplyController extends HttpServlet {
   HosMyReplyService hosMyReplyService;
   ReplyVO replyVO;
   HosImgDAO hosImgDAO;
   HosImgVO hosImgVO;
   HosMypageInfoService hosmypageinfoService;
   HosMypageInfoVO hosmypageinfoVO;

   public void init(ServletConfig config) throws ServletException {
          hosMyReplyService=new HosMyReplyService();
          replyVO=new ReplyVO();
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
      String nextPage=""; 
      String action=request.getPathInfo();
      System.out.println("요청이름 :" +action);
      
      try {  
    	  List<ReplyVO> hosMyReplyList=new ArrayList<ReplyVO>();
         if(action==null  || action.equals("/listMyReply.do") ){
        	    HttpSession session=request.getSession();
        	    //session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				String hos_code=(String)session.getAttribute("hos_code");
				hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
		           request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
				//===================이미지 
				hosImgVO=hosImgDAO.getHosPro(hos_code);
           		request.setAttribute("hosImgVO", hosImgVO);
        	  
        	   // hosMyReplyList = hosMyReplyService.listReply(hos_id);
        	    
        	    
        	    // 병원계정으로 로그인해서 답변관리 게시판에 불러오기 (페이징 때문에 8개씩)
        	    String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map hosReplyMap=hosMyReplyService.listReply(pagingMap, hos_id);
				hosReplyMap.put("section", section);
				hosReplyMap.put("pageNum", pageNum);
				 request.setAttribute("hosReplyMap", hosReplyMap);
        	    //
        	    int reply_count=hosMyReplyService.replyCount(hos_id);
        	    System.out.println(hos_id + " 가 작성한 "+reply_count +"개의 답변이 조회됨!!!");
        	    //request.setAttribute("hosMyReplyList", hosMyReplyList);
				request.setAttribute("reply_count", reply_count);
                nextPage="/hos_MyReplyInfo/listMyReply.jsp";
            }
         
         else if(action.equals("/hosReplyDel.do")) {
            	response.setContentType("text/html;charset=UTF-8");
            	HttpSession session=request.getSession();
        	   // session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
				String[] items=null;
                items=request.getParameterValues("del_chk");
        		if(items==null){
        			out.print("<script>");
        			out.print("alert('삭제할 답변을 선택해주세요!');");
        			out.print("location.href='" +request.getContextPath()+ "/hosReply/listMyReply.do" + "';");
        			out.print("</script>");
        			return;
        		}else {
        			
        			for(int i=0; i<items.length; i++) {
            			System.out.println( "items(답변등록번호) : " + items[i]);
            		}
        			hosMyReplyService.delReply(items);
        			out.print("<script>");
        			out.print("alert('답변이 삭제되었습니다.');");
        			out.print("location.href='" +request.getContextPath() + "/hosReply/listMyReply.do" + "';");
        			out.print("</script>");
        			return;
        		}
            			
            }else if(action.equals("/viewReply.do")) {
            	HttpSession session=request.getSession();
        	   // session = request.getSession();
				String hos_id=(String)session.getAttribute("log_id");
            	String a_code=request.getParameter("a_code");
				String q_code=hosMyReplyService.viewReply(a_code);				
				nextPage="/board/viewArticle.do?q_code="+q_code;	
            }
                     
      RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
      dispatcher.forward(request, response);
      } catch (Exception e) {
         System.out.println("HosMyReplyController : 요청 처리 작업 중 오류 발생");
         e.printStackTrace();
      }
      
   }

   
   
 	
 	
   
}