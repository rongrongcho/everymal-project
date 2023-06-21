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

import org.json.simple.JSONObject;

import forImg.HosImgDAO;
import forImg.HosImgVO;
import hosMypageInfo.HosMypageInfoService;
import hosMypageInfo.HosMypageInfoVO;


@WebServlet("/memberlist/*")
public class MemberlistController extends HttpServlet {
   MemberlistService memberlistService;
   MemberlistVO memberlistVO;
   MemberlistDAO memberlistDAO;

   public void init(ServletConfig config) throws ServletException {
	   	  memberlistService=new MemberlistService();
	   	  memberlistVO=new MemberlistVO();
	   	  memberlistDAO=new MemberlistDAO();
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
    	  List<MemberlistVO> memberList=new ArrayList<MemberlistVO>();
         if(action==null||action.equals("/list.do")){
        	    int member_count=memberlistService.memberCount();
        	   
        	    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
        	    String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map memberlistMap=memberlistService.list(pagingMap);
				memberlistMap.put("section", section);
				memberlistMap.put("pageNum", pageNum);
				 request.setAttribute("memberlistMap", memberlistMap);
        	    //
        	   
				request.setAttribute("member_count", member_count);
                nextPage="/administrator/userList.jsp";
         	}else if (action.equals("/glist.do")) {
         	    int member_count = memberlistService.gmemberCount();

         	    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
         	    String _section = request.getParameter("section");
         	    String _pageNum = request.getParameter("pageNum");
         	    int section = (_section == null) ? 1 : Integer.parseInt(_section);
         	    int pageNum = (_pageNum == null) ? 1 : Integer.parseInt(_pageNum);
         	    Map<String, Object> pagingMap = new HashMap<String, Object>();
         	    pagingMap.put("section", section);
         	    pagingMap.put("pageNum", pageNum);
         	    Map<String, Object> memberlistMap = memberlistService.Glist(pagingMap);
         	    memberlistMap.put("section", section);
         	    memberlistMap.put("pageNum", pageNum);
         	    request.setAttribute("memberlistMap", memberlistMap);

         	    request.setAttribute("member_count", member_count);
         	    System.out.println("일반회원수=====================" + member_count);

         	    // JSON 형식으로 응답 데이터 구성
         	    
         	    JSONObject responseJson = new JSONObject();
         	    responseJson.put("userList", memberlistMap.get("memberList"));

         	    response.setContentType("application/json");
         	    response.getWriter().write(responseJson.toString());

         	    return; 
             } else if (action.equals("/hlist.do")) {
                    int member_count = memberlistService.hosmemberCount();

                    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
                    String _section = request.getParameter("section");
                    String _pageNum = request.getParameter("pageNum");
                    int section = (_section == null) ? 1 : Integer.parseInt(_section);
                    int pageNum = (_pageNum == null) ? 1 : Integer.parseInt(_pageNum);
                    Map<String, Object> pagingMap = new HashMap<String, Object>();
                    pagingMap.put("section", section);
                    pagingMap.put("pageNum", pageNum);
                    Map<String, Object> memberlistMap = memberlistService.Hlist(pagingMap);
                    memberlistMap.put("section", section);
                    memberlistMap.put("pageNum", pageNum);
                    request.setAttribute("memberlistMap", memberlistMap);

                    request.setAttribute("member_count", member_count);
                    System.out.println("병원회원수=====================" + member_count);
                    
                    // JSON 형식으로 응답 데이터 구성
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("userList", memberlistMap.get("memberList"));

                    response.setContentType("application/json");
                    response.getWriter().write(responseJson.toString());
                    
                   return;
                

//         }else if (action.equals("/glist.do")) {
//        	    int member_count = memberlistService.gmemberCount();
//
//        	    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
//        	    String _section = request.getParameter("section");
//        	    String _pageNum = request.getParameter("pageNum");
//				int section=Integer.parseInt((_section==null)?"1":_section);
//				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
//        	    Map<String, Integer> pagingMap = new HashMap<String, Integer>();
//        	    pagingMap.put("section", section);
//        	    pagingMap.put("pageNum", pageNum);
//        	    Map memberlistMap = memberlistService.Glist(pagingMap);
//        	    memberlistMap.put("section", section);
//        	    memberlistMap.put("pageNum", pageNum);
//        	    request.setAttribute("memberlistMap", memberlistMap);
//        	    
//        	    request.setAttribute("member_count", member_count);
//        	    System.out.println("일반회원수====================="+member_count);
//        	    JSONObject responseJson = new JSONObject();
//        	    responseJson.put("userList", memberlistMap.get("memberList"));
//        	    response.setContentType("application/json");
//        	    response.getWriter().write(responseJson.toString());
//        	    nextPage = "/administer/userList.jsp";
//
//         }else if(action.equals("/hlist.do")) {
//        	 int member_count=memberlistService.hosmemberCount();
//        	   
//      	    // 병원계정으로 로그인해서 리뷰관리 게시판에 불러오기 (페이징 때문에 8개씩)
//      	    	String _section=request.getParameter("section");
// 				String _pageNum=request.getParameter("pageNum");
// 				int section = (_section == null) ? 1 : Integer.parseInt(_section);
//        	    int pageNum = (_pageNum == null) ? 1 : Integer.parseInt(_pageNum);
// 				Map<String,Object> pagingMap=new HashMap<String, Object>();
// 				pagingMap.put("section", section);
// 				pagingMap.put("pageNum", pageNum);
//// 				Map memberlistMap=memberlistService.Hlist(pagingMap);
// 				Map<String, Object> memberlistMap = memberlistService.Hlist(pagingMap);
// 				memberlistMap.put("section", section);
// 				memberlistMap.put("pageNum", pageNum);
// 				 request.setAttribute("memberlistMap", memberlistMap);
//      	   
// 				request.setAttribute("member_count", member_count);
// 				System.out.println("병원회원수====================="+member_count);
// 				JSONObject responseJson = new JSONObject();
//        	    responseJson.put("userList", memberlistMap.get("memberList"));
//        	    response.setContentType("application/json");
//        	    response.getWriter().write(responseJson.toString());
// 				nextPage = "/administer/userList.jsp";
         }else if(action.equals("/elist.do")) {
        	 
         }else if(action.equals("/alist.do")) {
        	 
         }
      RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
      dispatcher.forward(request, response);
      } catch (Exception e) {
         System.out.println("memberlistController : 요청 처리 작업 중 오류 발생");
         e.printStackTrace();
      }
      
   }
      
          
 	
 	
   
}