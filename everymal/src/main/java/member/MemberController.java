package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
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
//		String nextPage = null;
		PrintWriter out = response.getWriter();
		String action = request.getPathInfo();
		System.out.println("요청매핑이름 : " + action);
		if(action.equals("/addMember.do")) {
			String user_id = request.getParameter("user_id");
			String user_pwd = request.getParameter("user_pwd");
			String user_name = request.getParameter("user_name");
			String user_email = request.getParameter("user_email");
			String user_tel1 = request.getParameter("user_tel1");
			String user_tel2 = request.getParameter("user_tel2");
			String user_tel3 = request.getParameter("user_tel3");
			String user_tel = user_tel1+user_tel2+user_tel3;
			String user_addr1 = request.getParameter("user_addr1");
			String user_addr2 = request.getParameter("user_addr2");
			String user_zipcode = request.getParameter("user_zipcode");
			MemberVO memberVO = new MemberVO(user_id, user_pwd, user_name, user_tel, user_email, user_addr1, user_addr2, user_zipcode);
			memberDAO.addMember(memberVO);
			String userCode = memberDAO.selectUserCode(memberVO);
			out.print(userCode);
		}else if(action.equals("/idChk.do")) {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			String id = (String)request.getParameter("id");
			System.out.println("아이디 : "+id);
			MemberDAO dao= new MemberDAO();
			boolean overlappedID=dao.overlappedID(id);
			if(overlappedID==true) {
				out.print("not_usable");
			}else{
				out.print("usable");
			}
			System.out.println("overlappedID : "+overlappedID);
		}
	}
}
