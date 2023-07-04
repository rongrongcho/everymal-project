package com.myspring.stsproject.userMypage.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.forImg.dao.HosImgDAO;
import com.myspring.stsproject.forImg.vo.HosImgVO;
import com.myspring.stsproject.userMypage.dao.PetInfoDAO;
import com.myspring.stsproject.userMypage.dao.UserDAO;
import com.myspring.stsproject.userMypage.service.UserService;
import com.myspring.stsproject.userMypage.vo.PetInfoVO;
import com.myspring.stsproject.userMypage.vo.UserVO;

@Controller("petInfoController")
public class PetInfoControllerImpl implements PetInfoController{

	@Autowired
	PetInfoDAO petInfoDAO;
	
	@Autowired
	PetInfoVO petInfoVO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserVO userVO;
	
	@Autowired
	UserService userService;
	
	//pet 추가할때
	/*@Autowired
	AnimalDAO animalDAO;
	
	
	@Autowired
	AnimalVO animalVO;*/
	
	@Override
	@RequestMapping(value = "/user_Page/myPetList.do", method = RequestMethod.GET)
	public ModelAndView myPetList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		 ModelAndView mav = new ModelAndView(viewName);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		HttpSession session = request.getSession();
		String user_code=(String) session.getAttribute("user_code");
		user_code="use0001";
		System.out.println("user_code가 뭐냐>>>>>:"+user_code);
		model.addAttribute("user_code",user_code); //jsp로 값 넘겨줘야 할 때 사용
		List<PetInfoVO> petList=petInfoDAO.selectPetList(user_code);		
		mav.addObject("petList", petList);
		//===========회원 마이 페이지 프사 
		String user_id= (String)session.getAttribute("log_id");
		userVO=userService.calluserInfo(user_id);               
        mav.addObject("userVO", userVO);
		return mav;
	}

	@Override
	@RequestMapping(value = "/user_Page/modPet.do", method = RequestMethod.POST)
	public ModelAndView modPet( Model model, HttpServletRequest request,		
		HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		String viewName=(String) request.getAttribute("viewName");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String user_code=(String) session.getAttribute("user_code");
		user_code="use0001";
		 //ModelAndView mav = new ModelAndView(viewName);
		
		
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
		petInfoVO=new PetInfoVO(pet_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc);
		petInfoDAO.updatePet(petInfoVO);
		out=response.getWriter();
		out.print("<script>");
		out.print("alert('동물을 수정했습니다');");
		out.print("location.href='"+request.getContextPath()+"/user_Page/myPetList.do';");
		out.print("</script>");
		//ModelAndView mav=new ModelAndView("redirect:/petinfo/myPetList.do?user_id="+user_code);
		return null;
	}

}
