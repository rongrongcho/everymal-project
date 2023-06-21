package animal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/animal/*")
public class AnimalController extends HttpServlet {
	AnimalDAO animalDAO;
	public void init(ServletConfig config) throws ServletException {
		animalDAO = new AnimalDAO();
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
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("요청매핑이름 : " + action);
		if(action.equals("/addAnimal.do")) {
			String pet_name = request.getParameter("pet_name");
			int pet_age = Integer.parseInt(request.getParameter("pet_age"));
			String pet_sex = request.getParameter("pet_sex");
			String pet_types = request.getParameter("pet_types");
			String pet_number = request.getParameter("pet_number");
			String b_type = request.getParameter("b_type");
			float pet_weight = Float.parseFloat(request.getParameter("pet_weight"));
			String pet_etc = request.getParameter("pet_etc");
			String user_code = request.getParameter("user_code");
			AnimalVO animalVO = new AnimalVO(user_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc);
			animalDAO.addAnimal(animalVO);
			
//			nextPage = "/index.jsp";
//			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
//			dispatcher.forward(request, response);
			 
//			 out.print("<script>");
//			 out.print("alert('회원가입완료.');");
//			 out.print("location.href='"+request.getContextPath()+"/login.jsp';");
//			 out.print("</script>");
		}
	}
}
