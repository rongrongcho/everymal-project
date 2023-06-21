package userHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userMypage.UserService;
import userMypage.UserVO;



@WebServlet("/history/*")
public class HistoryController extends HttpServlet {
   //인스턴스 객체 선언 
   HistoryDAO historyDAO;
   HistoryVO historyVO;
   HistoryService historyService;
   UserVO userVO;
   UserService userService;
   private static String IMG_REPO="D:\\everymal_0517final\\imgRepo\\hos_images";
   

   
   public void init(ServletConfig config) throws ServletException {
      //딱 한번만 객체 생성 
      try {
         historyVO=new HistoryVO();
         historyDAO = new HistoryDAO();
         historyService=new HistoryService();
         userVO=new UserVO();
         userService=new UserService();
         System.out.println(" init  메서드 : 정상 수행 " );
         
      } catch (Exception e) {
         System.out.println("init  메서드 : 인스턴스 객체 생성 실패 ");
      }
   
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);

   }
   
   private void doHandle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      String nextPage=""; 
      String action=request.getPathInfo();
      System.out.println("HSC 요청 이름 : " + action);
      HttpSession session=request.getSession();

      //PrintWriter out;
      try {
         List<HistoryVO> historyList=new ArrayList<HistoryVO>(); 
         List<HistoryVO> recentReserv=new ArrayList<HistoryVO>();
         
         if(action==null || action.equals("/user_Page/myHosReserv.jsp")) {
            String log_id=(String)session.getAttribute("log_id");
            System.out.println("controller 로그인 아이디 :" + log_id);
            userVO=userService.calluserInfo(log_id); //회원 정보 불러옴 
              request.setAttribute("userVO", userVO);
            historyVO.setUser_id(log_id);   

            historyList=historyService.listHReserv(historyVO); // 지난 이용 내역 데이터 
            request.setAttribute("historyList", historyList);
            recentReserv=historyService.listHRecent(historyVO);//가장 최근 예약 데이터 
            request.setAttribute("recentList", recentReserv);
            nextPage="/user_History/myHosReserv.jsp";
            
         }else if(action.equals("/viewHistory.do") ) {

              request.setAttribute("userVO", userVO);
            
            String res_code=request.getParameter("res_code");
            historyVO=historyService.vHis(res_code);
            request.setAttribute("viewHis", historyVO);
            nextPage="/user_History/userResView.jsp";
         }
         
         
         
//         ============================택시 
         
         else if(action.equals("/myPetTaxiReserv.do" )|| action.equals("/user_History/myPetTaxiReserv.jsp")) {
            String log_id=(String)session.getAttribute("log_id");
            userVO=userService.calluserInfo(log_id); //회원 정보 불러옴 
              request.setAttribute("userVO", userVO);
              
            System.out.println("택정불controller 로그인 아이디 :" + log_id);
            String user_code=(String)session.getAttribute("user_code");
            System.out.println("택정불 cotroller user_code : " +user_code);
            historyVO.setUser_id(log_id);
            historyVO.setUser_code(user_code);
            
            
            historyList=historyService.listTxReserv(historyVO); // 지난 이용 내역 데이터 
            request.setAttribute("taxiHistory", historyList);
            recentReserv=historyService.listTxRecent(historyVO);//가장 최근 예약 데이터 
            request.setAttribute("taxiRecent", recentReserv);
            nextPage="/user_History/myPetTaxiReserv.jsp";
            
         }
            
         
         else if(action.equals("/viewTaxiRes.do")) {      
            String tx_res_code=request.getParameter("tx_res_code");
            historyVO=historyService.vTaxiHis(tx_res_code);
            request.setAttribute("viewHis", historyVO);
            nextPage="/user_History/userResTaxiView.jsp";
            //nextPage="/user_History/myPetTaxiReserv.jsp";
         }
         //nextPage로 포워딩
         RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
         dispatcher.forward(request, response);
         
      } catch (Exception e) {
         System.out.println("HistoryController : 요청 처리 중 에러");
         e.printStackTrace();
      }

      
   }//doHandle 끝 

}