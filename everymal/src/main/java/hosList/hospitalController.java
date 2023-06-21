package hosList;

import java.io.IOException;
import java.io.PrintWriter;
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

import forImg.HosImgDAO;
import forImg.HosImgVO;
import hosReservation.ReservationDAO;
import hosReservation.ReservationVO;
import hosReviewInfo.HosReviewService;
import hosReviewInfo.ReviewVO;


@WebServlet("/hospital/*")
public class hospitalController extends HttpServlet {
   hospitalDAO hospitalDAO;
   ReviewVO hosReviewVO;
   HosReviewService hosReviewService;
   HosImgVO hosImgVO;
    HosImgDAO hosImgDAO;
   
   
   public void init(ServletConfig config) throws ServletException {
      hospitalDAO = new hospitalDAO();
      hosReviewVO=new ReviewVO();
        hosReviewService=new HosReviewService();
        hosImgVO=new HosImgVO(); //이미지를 위해
        hosImgDAO=new HosImgDAO();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }
   
   private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      HttpSession session=request.getSession();
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      String nextPage = null;
      String action = request.getPathInfo();   // URL 요청명을 가져온다.
      System.out.println("요청매핑이름 : " + action);
      if(action.equals("/addHospital.do")) {
         String hos_id = request.getParameter("hos_id");
         String hos_username = request.getParameter("hos_username");
         String hos_usertel1 = request.getParameter("hos_usertel1");
         String hos_usertel2 = request.getParameter("hos_usertel2");
         String hos_usertel3 = request.getParameter("hos_usertel3");
         String hos_usertel = hos_usertel1+hos_usertel2+hos_usertel3;
         String hos_pwd = request.getParameter("hos_pwd");
         String hos_dno1 = request.getParameter("hos_dno1");
         String hos_dno2 = request.getParameter("hos_dno2");
         String hos_dno3 = request.getParameter("hos_dno3");
         long hos_dno = Long.parseLong(hos_dno1+hos_dno2+hos_dno3);
         String hos_name = request.getParameter("hos_name");
         String hos_tel1 = request.getParameter("hos_tel1");
         String hos_tel2 = request.getParameter("hos_tel2");
         String hos_tel3 = request.getParameter("hos_tel3");
         String hos_tel = hos_tel1+hos_tel2+hos_tel3;
         String hos_zipcode = request.getParameter("hos_zipcode");
         String hos_addr1 = request.getParameter("hos_addr1");
         String hos_addr2 = request.getParameter("hos_addr2");
         String hos_email = request.getParameter("hos_email");
         String hos_intro = request.getParameter("hos_intro");
         String hos_mailing = request.getParameter("hos_mailing");
         
//         ---------------------------------------------------
         
         String hos_24 = request.getParameter("hos_24");
         String hos_365_1 = request.getParameter("hos_365_1");
         String hos_365_2 = request.getParameter("hos_365_2");
         String hos_365_3 = request.getParameter("hos_365_3");
         String hos_365_4 = request.getParameter("hos_365_4");
         String hos_365_5 = request.getParameter("hos_365_5");
         String hos_365_6 = request.getParameter("hos_365_6");
         String hos_365_7 = request.getParameter("hos_365_7");
         
         String hos_365 = "";
         if(hos_365_1 != null && !hos_365_1.equals("") ){
            hos_365 += hos_365_1;
         }
         if(hos_365_2 != null && !hos_365_2.equals("") ){
            hos_365 += hos_365_2;
         }
         if(hos_365_3 != null && !hos_365_3.equals("") ){
            hos_365 += hos_365_3;
         }
         if(hos_365_4 != null && !hos_365_4.equals("") ){
            hos_365 += hos_365_4;
         }
         if(hos_365_5 != null && !hos_365_5.equals("") ){
            hos_365 += hos_365_5;
         }
         if(hos_365_6 != null && !hos_365_6.equals("") ){
            hos_365 += hos_365_6;
         }
         if(hos_365_7 != null && !hos_365_7.equals("") ){
            hos_365 += hos_365_7;
         }
         
         String hos_time_s = request.getParameter("hos_time_s");
         String hos_time_e = request.getParameter("hos_time_e");
         String hos_dog = request.getParameter("hos_dog");
         String hos_cat = request.getParameter("hos_cat");
         String hos_small = request.getParameter("hos_small");
         String hos_fish = request.getParameter("hos_fish");
         String hos_bird = request.getParameter("hos_bird");
         String hos_rep = request.getParameter("hos_rep");
         String hos_etc = request.getParameter("hos_etc");
         String hos_gs = request.getParameter("hos_gs");
         String hos_im = request.getParameter("hos_im");
         String hos_em = request.getParameter("hos_em");
         String hos_rm = request.getParameter("hos_rm");
         String hos_vac = request.getParameter("hos_vac");
         
         hospitalVO hosVO = new hospitalVO(hos_id, hos_username, hos_usertel, hos_pwd, hos_dno, hos_name, hos_tel, hos_zipcode, hos_addr1, hos_addr2, hos_email, hos_intro, hos_mailing, hos_24, hos_365, hos_time_s, hos_time_e, hos_dog, hos_cat, hos_small, hos_fish, hos_bird, hos_rep, hos_etc, hos_gs, hos_im, hos_em, hos_rm, hos_vac);
         hospitalDAO.addHos(hosVO);
         
         nextPage = "/index.jsp";
          

         
      }else if(action.equals("/Hospital_detail.do")) {
         String hos_code=request.getParameter("hos_code");
         hospitalVO result = hospitalDAO.selectHos(hos_code);
         List<ReviewVO> hosReviewList=new ArrayList<ReviewVO>();
           //==================롱이꺼
            hosImgVO=hosImgDAO.getHosImg(hos_code);    
            request.setAttribute("hosImgVO", hosImgVO); 
         
            int review_count=hosReviewService.reviewCount(hos_code);// (hosReview패키지 이용)
           hosReviewList = hosReviewService.listRev2(hos_code); //리뷰 전부 가져오기 (hosReview패키지 이용)
           float review_avg_result=hosReviewService.reviewAvg(hos_code); //평점구하기 (hosReview패키지 이용)
           String review_avg=String.format("%.2f", review_avg_result); //평점 소수 2자리까지 출력 
           request.setAttribute("hosReviewList", hosReviewList);
         request.setAttribute("review_count", review_count);
         request.setAttribute("review_avg", review_avg);
         System.out.println(result);
     	String tel=result.getHos_tel();
    	String ftel=tel.substring(tel.length()-4, tel.length());
    	String btel=tel.substring(tel.length()-7, tel.length()-4);
    	String ltel=tel.substring(0, tel.length()-7);
    	tel=ltel+"-"+btel+"-"+ftel;
    	result.setHos_tel(tel);
         request.setAttribute("hosData", result);   
         //request.setAttribute("reviewList", reviewList);
         nextPage = "/hos_List/hospital_detail_page.jsp";
           

      }else if(action.equals("/addReview.do")) {
         hospitalVO hosvo=new hospitalVO();
         
         String user_code22=(String)session.getAttribute("user_code");
         System.out.println(user_code22);
         if(user_code22==null) {
            out.println("<script>");
            out.println("alert('로그인 후 리뷰작성을 해주세요.');");
            out.println("</script>");
         }
         String user_id= (String)session.getAttribute("log_id");
         String hos_code=request.getParameter("hos_code");
         System.out.println("병원코드 "+hos_code);
         String rv_title=request.getParameter("rv_title");
         int rv_rate=Integer.parseInt(request.getParameter("rv_rate"));
         String rv_text=request.getParameter("rv_text");
         String hos_name=request.getParameter("hos_name");
         hosvo.setUser_id(user_id);
         hosvo.setRv_code(hospitalDAO.getNewRevCode());
         hosvo.setRes_code(hospitalDAO.getResCode(user_code22,hos_code));
         hosvo.setHos_name(hos_name);
         System.out.println("병원이름"+hos_name);
         //확인 요망!!!!!!!!!!
//         if(hospitalDAO.getResCode(user_code22,hos_code)==null) {
//            out.println("<script>");
//            out.println("alert('병원을 이용한 고객만 리뷰 작성이 가능합니다.');");
//            out.println("</script>");
//         }
         hosvo.setRv_text(rv_text);
         hosvo.setRv_title(rv_title);
         hosvo.setRv_rate(rv_rate);
         hosvo.setHos_code(hos_code);
         hospitalDAO.addreview(hosvo);
         out.println("<script>");
         out.println("setTimeout(function(){ window.close(); }, 1000);");
         out.println("opener.location.reload();");
         out.println("</script>");
      
      
      }else if(action.equals("/reservationForm.do")) {
            String hos_code=request.getParameter("hos_code");
            hospitalVO result = hospitalDAO.selectHos(hos_code);
            request.setAttribute("hosData", result);    
            nextPage="/hos_Reservation/reservationForm.jsp";
        }else if(action.equals("/hos_Reservation/listPopup.jsp")){
           ReservationDAO reservationDAO=new ReservationDAO();
            List<ReservationVO> animalList=new ArrayList<ReservationVO>();
            String user_code=(String)session.getAttribute("user_code");
            animalList=reservationDAO.animalList(user_code);
            request.setAttribute("animalList", animalList);
            System.out.println("ㄴ이ㅓㄹ니ㅏ러 니ㅏ어리 "+animalList);
            nextPage="/hos_Reservation/listPopup.jsp";
        }else if(action.equals("/reservation.do")) {  //예약하기 
           String user_id=(String)session.getAttribute("log_id");
           System.out.println("아니 또 왜.........."+user_id);
            String hos_code=request.getParameter("hos_code");
            hospitalVO result = hospitalDAO.selectHos(hos_code);
            request.setAttribute("hosData", result);
            
            ReservationVO resVO= new ReservationVO();
            ReservationDAO reservationDAO = new ReservationDAO();
            String user_code= (String)session.getAttribute("user_code");
            if(user_code == null) {
                out.println("<script>");
                out.println("alert('예약을 하시려면 로그인을 먼저 해주세요.');");
                out.println("history.back();"); // 이전 페이지로 이동
                out.println("</script>");
            }
            String user_name=request.getParameter("user_name");
            String tel_front=request.getParameter("tel_front");
            String tel_end=request.getParameter("tel_end");
            String user_tel=tel_front+tel_end;
            String hos_name=request.getParameter("hos_name");
            String pet_name=request.getParameter("pet_name");
            String pet_code=request.getParameter("pet_code");
            String pet_number=request.getParameter("pet_number");
            System.out.println("컨트롤러 number================="+pet_number);
            String pet_sex=request.getParameter("pet_sex");
            System.out.println("컨트롤러 pet_sex================="+pet_sex);
            String pet_types=request.getParameter("pet_types");
            String b_type=request.getParameter("b_type");
            System.out.println("컨트롤러 b_type================="+b_type);
            String res_date=request.getParameter("res_date");
            String res_time=request.getParameter("res_time");
            String res_etc=request.getParameter("res_etc");
         int pet_age=Integer.parseInt(request.getParameter("pet_age"));
            float pet_weight=Float.parseFloat(request.getParameter("pet_weight"));
            
            
            String [] hosSub=request.getParameterValues("hos_sub");
            String hos_sub=String.join(",", hosSub);
            System.out.println("컨트롤러 진료과목================="+hos_sub);
            
            resVO.setUser_id(user_id);
            resVO.setHos_code(hos_code);
            resVO.setRes_code(reservationDAO.getNewReservCode());
            resVO.setUser_code(user_code);
            resVO.setUser_name(user_name);
            resVO.setUser_tel(user_tel);
            resVO.setPet_name(pet_name);
            resVO.setPet_code(pet_code);
            resVO.setPet_number(pet_number);
            resVO.setPet_sex(pet_sex);
            resVO.setPet_types(pet_types);
            resVO.setPet_age(pet_age);
            resVO.setB_type(b_type);
            resVO.setRes_date(res_date);
            resVO.setRes_etc(res_etc);
            resVO.setPet_weight(pet_weight);
            resVO.setHos_name(hos_name);
            resVO.setHos_sub(hos_sub);
            resVO.setRes_time(res_time);
            reservationDAO.hosReserv(resVO);
            
            out=response.getWriter();
            out.print("<script>");
            out.print("alert('예약을 완료했습니다.'); ");
            out.print("location.href='" +request.getContextPath() + "/hosfilter';"); //내 정보에서 예약페이지 확인으로 넘어가도록 
            out.print("</script>");
        }
      
      
      else if(action.equals("/idChk.do")) {
         request.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");
         out = response.getWriter();
         String id = (String)request.getParameter("id");
         System.out.println("아이디 : "+id);
         hospitalDAO dao= new hospitalDAO();
         boolean overlappedID=dao.overlappedID(id);
         if(overlappedID==true) {
            out.print("not_usable");
         }else{
            out.print("usable");
         }
         System.out.println("overlappedID : "+overlappedID);
         return;
      }
      
      RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
      dispatcher.forward(request, response);
      }
   
}