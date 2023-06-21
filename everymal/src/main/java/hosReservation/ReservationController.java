package hosReservation;

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

import hosList.hospitalDAO;
import hosList.hospitalVO;


@WebServlet("/reserv/*")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReservationDAO reservationDAO;
	ReservationVO reservationVO;
	hospitalDAO hospitalDAO;
	hospitalVO hospitalVO;
	
	public void init(ServletConfig config) throws ServletException {
		reservationDAO= new ReservationDAO(); //시작할때 객체 만들었기 때문에, doHandle에서 그냥 사용만 해도 가능
		reservationVO= new ReservationVO();
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
		
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("log_id"));
		System.out.println(session.getAttribute("user_code"));
		

		if(action.equals("/hos_Reservation/listPopup.jsp")){
        	List<ReservationVO> animalList=new ArrayList<ReservationVO>();
        	String user_code=(String)session.getAttribute("user_code");
        	animalList=reservationDAO.animalList(user_code);
        	request.setAttribute("animalList", animalList);
        	System.out.println("ㄴ이ㅓㄹ니ㅏ러 니ㅏ어리 "+animalList);
        	nextPage="/hos_Reservation/listPopup.jsp";
        }

		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage); //받은 정보 nextPage(조건문별로 다르다)로 전달
		dispatcher.forward(request, response);
	}

}
