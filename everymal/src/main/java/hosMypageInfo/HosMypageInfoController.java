package hosMypageInfo;

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

@WebServlet("/hosMypageInfo/*")
public class HosMypageInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HosMypageInfoDAO hosmypageinfoDAO;
	HosMypageInfoVO hosmypageinfoVO;
	HosMypageInfoService hosmypageinfoService;
	HosImgVO hosImgVO;
	HosImgDAO hosImgDAO;
	
	public void init(ServletConfig config) throws ServletException {
	   hosmypageinfoDAO= new HosMypageInfoDAO(); //시작할때 객체 만들었기 때문에, doHandle에서 그냥 사용만 해도 가능
	   hosmypageinfoVO= new HosMypageInfoVO();
	   hosmypageinfoService= new HosMypageInfoService();
	   hosImgVO=new HosImgVO();
	   hosImgDAO=new HosImgDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String nextPage=null; 
		String action=request.getPathInfo(); 
		
		System.out.println("요청매핑이름: "+action);
		List<HosMypageInfoVO> informationList=new ArrayList<HosMypageInfoVO>();
		
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("log_id"));
		
		if(action==null||action.equals("/hos_MypageInfo/hosMypage.jsp")) {
			String hos_id=request.getParameter("hos_id");
			hosmypageinfoVO= new HosMypageInfoVO();
			hosmypageinfoVO.setHos_id(hos_id);
			//=======================이미지 ===================
			String hos_code=(String)session.getAttribute("hos_code");
			hosImgVO=hosImgDAO.getHosPro(hos_code);
       		request.setAttribute("hoImgVO", hosImgVO);
			
			nextPage="/hos_MypageInfo/hosMypage.jsp";

		}else if(action.equals("/isValid.do")) {
			String hos_id=request.getParameter("hos_id");
			String hos_pwd=request.getParameter("hos_pwd");
			hosmypageinfoVO= new HosMypageInfoVO();
			hosmypageinfoVO.setHos_id(hos_id);
			hosmypageinfoVO.setHos_pwd(hos_pwd);
			Boolean result=hosmypageinfoDAO.isValid(hosmypageinfoVO);
			
			if(result) {
				session.setAttribute("isLogon", true);
		        String _hos_id= (String)session.getAttribute("log_id");
		        hosmypageinfoVO=hosmypageinfoService.callhosInfo(_hos_id);
		        request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
		        //=========================이미지 ===================
				String hos_code=(String)session.getAttribute("hos_code");
				hosImgVO=hosImgDAO.getHosPro(hos_code);
	       		request.setAttribute("hosImgVO", hosImgVO);

		        nextPage= "/hos_MypageInfo/hosUserInformation.jsp";

			}else {
				out.print("<script>alert('비밀번호를 다시 확인해주세요.');window.history.back();</script>");

			}
		}else if(action.equals("/myInfo.do")) {

           String hos_id= (String)session.getAttribute("log_id");
           hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
           request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
           //=======================이미지=========================
			String hos_code=(String)session.getAttribute("hos_code");
			hosImgVO=hosImgDAO.getHosPro(hos_code);
      		request.setAttribute("hosImgVO", hosImgVO);

           nextPage= "/hos_MypageInfo/hosUserInformation.jsp";
        }else if(action.equals("/modhosInfo.do")) {
        	String hos_id=(String)session.getAttribute("log_id");
        	String hos_code=(String)session.getAttribute("hos_code");
        	String hos_pwd=request.getParameter("hos_pwd");
        	String hos_username=request.getParameter("hos_username");
        	String usertel_front=request.getParameter("usertel_front");
        	String usertel_end=request.getParameter("usertel_end");
        	Long hos_dno=Long.parseLong(request.getParameter("hos_dno"));
        	String hos_email=request.getParameter("hos_email");
        	
        	hosmypageinfoVO.setHos_id(hos_id);
        	hosmypageinfoVO.setHos_code(hos_code);
        	hosmypageinfoVO.setHos_pwd(hos_pwd);
        	hosmypageinfoVO.setHos_username(hos_username);
        	hosmypageinfoVO.setHos_dno(hos_dno);
        	hosmypageinfoVO.setHos_email(hos_email);
        	hosmypageinfoVO.setUsertel_front(usertel_front);
        	hosmypageinfoVO.setUsertel_end(usertel_end);
        	String hos_usertel=usertel_front+usertel_end;
        	hosmypageinfoDAO.modhosInfo(hosmypageinfoVO);
        	
            nextPage= "/hosMypageInfo/myInfo.do";
            
        }else if(action.equals("/myHosInfo.do")) {
        	String hos_id=(String)session.getAttribute("log_id");
        	hosmypageinfoVO=hosmypageinfoService.callhosInfo(hos_id);
            request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
            //=======================이미지=========================
 			String hos_code=(String)session.getAttribute("hos_code");
 			hosImgVO=hosImgDAO.getHosPro(hos_code);
       		request.setAttribute("hosImgVO", hosImgVO);
       		
            nextPage= "/hos_MypageInfo/hosInformation.jsp";
        }else if(action.equals("/modmyHosInfo.do")) {
        	String hos_id=(String)session.getAttribute("log_id");
        	String hos_code=(String)session.getAttribute("hos_code");
        	String hos_name=request.getParameter("hos_name");
        	String tel_front=request.getParameter("tel_front");
        	String tel_end=request.getParameter("tel_end");
        	String hos_intro=request.getParameter("hos_intro");
        	String hos_zipcode=request.getParameter("hos_zipcode");
        	String hos_addr1=request.getParameter("hos_addr1");
        	String hos_addr2=request.getParameter("hos_addr2");
        	
        	String [] hos365 = request.getParameterValues("hos_365");
        	String hos365Str = String.join(",", hos365);

			String hos_time_s=request.getParameter("hos_time_s");
			String hos_time_e=request.getParameter("hos_time_e");
        	
        	String hos_dog=request.getParameter("hos_dog");
        	String hos_dog_value="0";
        	if(hos_dog!=null && hos_dog.equals("1")) {
        		hos_dog_value="1";
        	}
        	String hos_cat=request.getParameter("hos_cat");
        	String hos_cat_value="0";
        	if(hos_cat!=null && hos_cat.equals("1")) {
        		hos_cat_value="1";
        	}
        	String hos_small=request.getParameter("hos_small");
        	String hos_small_value="0";
        	if(hos_small!=null && hos_small.equals("1")) {
        		hos_small_value="1";
        	}
        	String hos_fish=request.getParameter("hos_fish");
        	String hos_fish_value="0";
        	if(hos_fish!=null && hos_fish.equals("1")) {
        		hos_fish_value="1";
        	}
        	String hos_bird=request.getParameter("hos_bird");
        	String hos_bird_value="0";
        	if(hos_bird!=null && hos_bird.equals("1")) {
        		hos_bird_value="1";
        	}
        	String hos_rep=request.getParameter("hos_rep");
        	String hos_rep_value="0";
        	if(hos_rep!=null && hos_rep.equals("1")) {
        		hos_rep_value="1";
        	}
        	String hos_etc=request.getParameter("hos_etc");
        	String hos_etc_value="0";
        	if(hos_etc!=null && hos_etc.equals("1")) {
        		hos_etc_value="1";
        	}
        	
        	String hos_gs=request.getParameter("hos_gs");
        	String hos_gs_value="0";
        	if(hos_gs!=null && hos_gs.equals("1")) {
        		hos_gs_value="1";
        	}
        	String hos_im=request.getParameter("hos_im");
        	String hos_im_value="0";
        	if(hos_im!=null && hos_im.equals("1")) {
        		hos_im_value="1";
        	}
        	String hos_em=request.getParameter("hos_em");
        	String hos_em_value="0";
        	if(hos_em!=null && hos_em.equals("1")) {
        		hos_em_value="1";
        	}
        	String hos_rm=request.getParameter("hos_rm");
        	String hos_rm_value="0";
        	if(hos_rm!=null && hos_rm.equals("1")) {
        		hos_rm_value="1";
        	}
        	String hos_vac=request.getParameter("hos_vac");
        	String hos_vac_value="0";
        	if(hos_vac!=null && hos_vac.equals("1")) {
        		hos_vac_value="1";
        	}
        	String hos_24=request.getParameter("hos_24");
        	String hos_24_value="0";
        	if(hos_24!=null && hos_24.equals("1")) {
        		hos_24_value="1";
        	}
        	
//        	hosmypageinfoVO= new HosMypageInfoVO();
        	hosmypageinfoVO.setHos_code(hos_code);
            hosmypageinfoVO.setHos_id(hos_id);
            hosmypageinfoVO.setHos_name(hos_name);
            hosmypageinfoVO.setTel_front(tel_front);
            hosmypageinfoVO.setTel_end(tel_end);
            hosmypageinfoVO.setHos_intro(hos_intro);
            hosmypageinfoVO.setHos_zipcode(hos_zipcode);
            hosmypageinfoVO.setHos_addr1(hos_addr1);
            hosmypageinfoVO.setHos_addr2(hos_addr2);
            
            hosmypageinfoVO.setHos_dog(hos_dog_value);
            hosmypageinfoVO.setHos_cat(hos_cat_value);
            hosmypageinfoVO.setHos_small(hos_small_value);
            hosmypageinfoVO.setHos_fish(hos_fish_value);
            hosmypageinfoVO.setHos_bird(hos_bird_value);
            hosmypageinfoVO.setHos_rep(hos_rep_value);
            hosmypageinfoVO.setHos_etc(hos_etc_value);
            
            hosmypageinfoVO.setHos_gs(hos_gs_value);
            hosmypageinfoVO.setHos_im(hos_im_value);
            hosmypageinfoVO.setHos_em(hos_em_value);
            hosmypageinfoVO.setHos_rm(hos_rm_value);
            hosmypageinfoVO.setHos_vac(hos_vac_value);
            hosmypageinfoVO.setHos_24(hos_24_value);

			hosmypageinfoVO.setHos_time_s(hos_time_s);
			hosmypageinfoVO.setHos_time_e(hos_time_e);
            System.out.println("나오나와노아노아ㅗ난와놔오나와노"+hos_time_s); 
        	
        	String hos_tel=tel_front+tel_end;
        	hosmypageinfoVO.setHos_tel(hos_tel);
        	hosmypageinfoVO.setHos_365(hos365Str);
        	hosmypageinfoDAO.modmyHosInfo(hosmypageinfoVO);
        	
        	nextPage= "/hosMypageInfo/myHosInfo.do";
        }
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage); //받은 정보 nextPage(조건문별로 다르다)로 전달
		dispatcher.forward(request, response);
	}

}