package com.myspring.stsproject.userMypage.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.forImg.dao.HosImgDAO;
import com.myspring.stsproject.forImg.vo.HosImgVO;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;
import com.myspring.stsproject.userMypage.dao.UserDAO;
import com.myspring.stsproject.userMypage.service.UserService;
import com.myspring.stsproject.userMypage.vo.UserVO;

@Controller("userController")
public class UserControllerImpl implements UserController{
	@Autowired
	HosImgVO hosImgVO;
	
	@Autowired
	HosImgDAO hosImgDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserVO userVO;
	
	@Autowired
	UserService userService;
	private static String IMG_REPO="D:\\everymal_0517final\\imgRepo\\user_profil";
	List<UserVO> reviewList=new ArrayList<UserVO>();
	List<UserVO> qList=new ArrayList<UserVO>();
	
	
	@Override
	@RequestMapping(value = "/user_Page/isValidPwd.do", method = RequestMethod.GET)
	public ModelAndView isValidPwd(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		String user_id=request.getParameter("user_id");
		userVO.setUser_id(user_id);
        userVO=userService.calluserInfo(user_id); //회원 정보 불러옴 		
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}


	@Override
	@RequestMapping(value = "/user_Page/isValid.do", method = RequestMethod.POST)
	public ModelAndView isValid(Model model, UserVO userVO, String hos_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session=request.getSession();
		 ModelAndView mav = new ModelAndView();
		String user_id=request.getParameter("user_id");
		String user_pwd=request.getParameter("user_pwd");
		
		userVO.setUser_id(user_id);
		userVO.setUser_pwd(user_pwd);	
		Boolean result=userDAO.isValid(userVO);	
		System.out.println("Booooooooolean 결과:" +result);
		if(result) {
			session.setAttribute("isLogon", true);		
	        String _user_id= (String)session.getAttribute("log_id");
	        userVO=userService.calluserInfo(_user_id); //회원 정보 불러옴 
	        request.setAttribute("userVO", userVO);
	        //model.addAttribute("hos_code",hos_code);
	        mav.setViewName("redirect:/user_Page/myInfo.do?user_id=" + user_id);

		}else {
			PrintWriter out=response.getWriter();
			out.print("<script>alert('비밀번호를 다시 확인해주세요.');window.history.back();</script>");
			rAttr.addAttribute("result", "failed");
			mav.setViewName("redirect:/user_Page/isValidPwd.do");
		}
		return mav;
	}


	@Override
	@RequestMapping(value = "/user_Page/myInfo.do", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		String user_id= (String)session.getAttribute("log_id");
		
        userVO=userService.calluserInfo(user_id);
        System.out.println("제발용" + userVO.getUser_imgName());
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("userVO", userVO);
		return mav;
	}


	
}
