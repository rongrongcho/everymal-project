package userMypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import animal.AnimalDAO;
import animal.AnimalVO;


@WebServlet("/petinfo/*")
public class PetInfoController extends HttpServlet {
	PetInfoDAO petInfoDAO;
	PetInfoVO petInfoVO;
	AnimalDAO animalDAO;
	AnimalVO animalVO;
	//=================이미지 프사 추가 
	UserVO userVO;
	UserService userService;
	
	//============================
	@Override
	public void init() throws ServletException {
		petInfoDAO = new PetInfoDAO();
		petInfoVO = new PetInfoVO();
		userVO=new UserVO();
		userService=new UserService();
		animalDAO = new AnimalDAO();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet호출");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost호출");
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		String nextPage="";
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action=request.getPathInfo();
		String user_code=(String) session.getAttribute("user_code");
		System.out.println("요청 이름 : "+action);
		try {
			if(action.equals("/myPetList.do")) {
				List<PetInfoVO> petList=petInfoDAO.selectPetList(user_code);
				request.setAttribute("petList", petList);
				//===========회원 마이 페이지 프사 
				String user_id= (String)session.getAttribute("log_id");
				userVO=userService.calluserInfo(user_id);
	            request.setAttribute("userVO", userVO);
				//===========================================
				nextPage="/user_Page/myPet_Info.jsp";
			}else if(action.equals("/modPet.do")) {
				String pet_code=request.getParameter("pet_code");
				String pet_name=request.getParameter("name");
				int pet_age=Integer.parseInt(request.getParameter("age"));
				String pet_sex=request.getParameter("gender");
				String pet_types=request.getParameter("petRace");
				String pet_number=request.getParameter("perNumber");
				String b_type=request.getParameter("bloodType");
				float pet_weight=Float.parseFloat(request.getParameter("weight"));
				String pet_etc=request.getParameter("notice");
				System.out.println(pet_code);
				System.out.println(pet_name);
				PetInfoVO petInfoVO=new PetInfoVO(pet_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc);
				petInfoDAO.updatePet(petInfoVO);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('동물을 수정했습니다');");
				out.print("location.href='"+request.getContextPath()+"/petinfo/myPetList.do';");
				out.print("</script>");
				return;
			}
			else if (action.equals("/removePetInfo.do")) {
				String pet_code=request.getParameter("pet_code");
				System.out.println("삭제수행");
				petInfoDAO.removePetInfo(pet_code);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('동물을 삭제했습니다');");
				out.print("location.href='"+request.getContextPath()+"/petinfo/myPetList.do';");
				out.print("</script>");
				return;
			}
			else if (action.equals("/addPet.do")) {
				String pet_name=request.getParameter("name");
				int pet_age=Integer.parseInt(request.getParameter("age"));
				String pet_sex=request.getParameter("gender");
				String pet_types=request.getParameter("petRace");
				String pet_number=request.getParameter("perNumber");
				String b_type=request.getParameter("bloodType");
				float pet_weight=Float.parseFloat(request.getParameter("weight"));
				String pet_etc=request.getParameter("notice");
				System.out.println(pet_name);
//				PetInfoVO petInfoVO=new PetInfoVO(pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc, user_code);
//				petInfoDAO.insertPet(petInfoVO);
				AnimalVO animalVO = new AnimalVO(user_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc);
				animalDAO.addAnimal(animalVO);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('동물을 추가했습니다');");
				out.print("location.href='"+request.getContextPath()+"/petinfo/myPetList.do';");
				out.print("</script>");
				return;
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("팻정보 구현중 오류 발생!");
			e.printStackTrace();
		}
	}
}
