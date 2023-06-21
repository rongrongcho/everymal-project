package withdrawal;

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

@WebServlet("/withdrawal/*")
public class WithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WithdrawalDAO withdrawalDAO;
	WithdrawalVO withdrawalVO;
	
	public void init(ServletConfig config) throws ServletException {
	   withdrawalDAO= new WithdrawalDAO(); //시작할때 객체 만들었기 때문에, doHandle에서 그냥 사용만 해도 가능
	   withdrawalVO= new WithdrawalVO();
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
		List<WithdrawalVO> withdrawalList=new ArrayList<WithdrawalVO>();
		
		if(action==null||action.equals("/updateEnabled.do")) {
			HttpSession session=request.getSession();
			String id=(String)session.getAttribute("log_id");
			System.out.println("아이디ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ"+id);
			if(session!=null&&id!=null) {
				withdrawalDAO.updateEnabled(id);
				nextPage="/index.jsp";
			}else {
				out.println("<script>alert('회원일 경우에만 탈퇴가 가능합니다.')</script>");
				out.println("<script>window.location.href='/index.jsp';</script>");
				out.close();
				return;
			}
        }
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage); //받은 정보 nextPage(조건문별로 다르다)로 전달
		dispatcher.forward(request, response);
	}

}