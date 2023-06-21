package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logindb/*")
public class LoginServletdb extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		//로그아웃
		String action=request.getPathInfo();
		try {
			if (action.equals("/logout")) {
				HttpSession session = request.getSession();
				//세션을 가져와 전부 삭제해서 로그아웃을 구현한다
				session.invalidate();
				out.print("<script>");
				out.print("alert('로그아웃 완료!');");
				out.print("location.href='"+request.getContextPath()+"/index.jsp';");
				out.print("</script>");
				return;
			}else if(action.equals("/searchId")) {
				boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
				MemberVO memVO=new MemberVO();
				memVO.setHos_chk(hos_chk);
				if(hos_chk) {
					String name=request.getParameter("rname2");
					String hos_dno=request.getParameter("work_no1")+'-'+request.getParameter("work_no2")+'-'+request.getParameter("work_no3");
					memVO.setName(name);
					memVO.setHos_dno(hos_dno);
				}else {
					String name=request.getParameter("rname1");
					String email=request.getParameter("ident")+'@'+request.getParameter("ident1");
					memVO.setName(name);
					memVO.setEmail(email);
				}
				MemberDAO dao = new MemberDAO();
				String id = dao.searchId(memVO);
				out.print("<script>");
				if(id==null) {
					out.print("alert('회원정보가 존재하지 않습니다!');");
				}else {
					id=id.substring(0, 3)+"***";
					out.print("alert('회원님의 아이디는 "+id+" 입니다!');");
				}
				out.print("location.href='"+request.getContextPath()+"/login.jsp';");
				out.print("</script>");
				return;
			}else if(action.equals("/searchPass")) {
				boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
				MemberVO memVO=new MemberVO();
				memVO.setHos_chk(hos_chk);
				if(hos_chk) {
					String name=request.getParameter("rname2");
					String id=request.getParameter("rid2");
					String hos_dno=request.getParameter("work_no1")+'-'+request.getParameter("work_no2")+'-'+request.getParameter("work_no3");
					memVO.setName(name);
					memVO.setId(id);
					memVO.setHos_dno(hos_dno);
				}else {
					String name=request.getParameter("rname1");
					String id=request.getParameter("rid1");
					String email=request.getParameter("ident")+'@'+request.getParameter("ident1");
					memVO.setName(name);
					memVO.setId(id);
					memVO.setEmail(email);
				}
				MemberDAO dao = new MemberDAO();
				boolean result = dao.searchPass(memVO);
				out.print("<script>");
				if(result) {
					out.print("alert('회원님의 이메일로 비밀번호를 재설정 할 수 있는 링크를 보냈습니다!');");
				}else {
					out.print("alert('회원정보가 존재하지 않습니다!');");
				}
				out.print("location.href='"+request.getContextPath()+"/login.jsp';");
				out.print("</script>");
				return;
			}
		}
		catch (Exception e) {
			System.out.println("로그아웃 구현중 오류 발생");
		}
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
		MemberVO memVO = new MemberVO();
		memVO.setId(user_id);
		memVO.setPwd(user_pwd);
		memVO.setHos_chk(hos_chk);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memVO); //가입이 되어있는지 확인하는 메서드
		if(result) {
			//회원정보 확인=>세션 생성=> 로그인정보 세션 셋팅
			
			HttpSession session = request.getSession(); //없으면 생성 있으면 가져옴
			session.invalidate();
			session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			MemberVO memInfo=dao.memberInfo(memVO);
			String dest="";
			if(hos_chk) {
				session.setAttribute("isHos", true);
				session.setAttribute("hos_code", memInfo.getCode());
				session.setAttribute("hos_name", memInfo.getHos_name());
				//dest="/HosMypageInfo/HosMypage.jsp';";
				dest="/index.jsp';";
			}else {
				session.setAttribute("user_code", memInfo.getCode());
				session.setAttribute("isHos", false);
//				dest="/index.jsp';";
				dest="/index.jsp';";
			}
			out.print("<script>");
			out.print("alert('로그인 성공!');");
			out.print("location.href='"+request.getContextPath()+dest);
			out.print("</script>");
		}else {
			//회원정보 X => 다시 로그인하기
			out.print("<script>");
			out.print("alert('회원정보가 틀렸습니다.');");
			out.print("location.href='"+request.getContextPath()+"/login.jsp';");
			out.print("</script>");
		}
	}

}
