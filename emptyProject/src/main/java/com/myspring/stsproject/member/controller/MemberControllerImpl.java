package com.myspring.stsproject.member.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.member.service.MemberService;
import com.myspring.stsproject.member.vo.MemberVO;


@Controller("memberController") 
public class MemberControllerImpl extends MultiActionController implements MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}


	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		인터셉터에서 만들어준 viewName을 가져온다
		String viewName=(String) request.getAttribute("viewName");
		List memberList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberList", memberList); 
		return mav;
	}
	


	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.addMember(memberVO); 
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		return mav;
	}


	@RequestMapping(value = "/member/join_user.do", method = RequestMethod.GET)
	public ModelAndView join_user(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/member/join_hospital.do", method = RequestMethod.GET)
	public ModelAndView join_hospital(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}


	@RequestMapping(value = "/member/modMemberForm.do", method = RequestMethod.GET)
	public ModelAndView modMemberForm(@RequestParam("id") String id, MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String viewName=(String) request.getAttribute("viewName");

		memberVO=memberService.findMember(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("member", memberVO);
		return mav;
	}


	@Override
	@RequestMapping(value = "/member/updateMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("memberVO")MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.updateMember(memberVO);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}


	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	

	
	@RequestMapping(value = "/member/loginForm.do", method = RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("member") MemberVO member, @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/member/join_choice.do", method = RequestMethod.GET)
	public ModelAndView join_choice(@ModelAttribute("member") MemberVO member, @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/member/search_id.do", method = RequestMethod.GET)
	public ModelAndView search_id(@ModelAttribute("member") MemberVO member, @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/member/search_pass.do", method = RequestMethod.GET)
	public ModelAndView search_pass(@ModelAttribute("member") MemberVO member, @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	
//	  @Override
//	  @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
//	  public ModelAndView login(@ModelAttribute("memberVO") MemberVO memberVO,
//	  RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse
//	  response) throws Exception { 
//		  ModelAndView mav=new ModelAndView();
//	  memberVO=memberService.login(memberVO); 
//	  if(memberVO!=null) { 
//		  HttpSession session=request.getSession(); 
//	  session.setAttribute("member", memberVO);
//	  session.setAttribute("isLogOn", true); 
//	  String action=(String)session.getAttribute("action");
//	  session.removeAttribute("action"); 
//	  if(action!=null) { //redirect 포워딩 기술중 하나 dispacher도 있음 
//		  mav.setViewName("redirect:"+action); 
//		  }else {
//	  mav.setViewName("redirect:/member/listMembers.do"); } 
//	  }else {
//	  rAttr.addAttribute("result", "loginFailed");
//	  mav.setViewName("redirect:/member/loginForm.do"); 
//	  } return mav; 
//	  }
	 
	
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("memberVO") MemberVO memberVO,  RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		boolean result = false;
		
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		memberVO.setHos_chk(hos_chk);
		
		/* memberVO=memberService.login(memberVO); */
		result=memberService.login(memberVO);
		if(result == true) {
//			HttpSession session=request.getSession();
//			session.setAttribute("member", memberVO);
//			session.setAttribute("isLogOn", true);
//			String action=(String)session.getAttribute("action");
//			session.removeAttribute("action");
//			if(action!=null) {
//				//redirect 포워딩 기술중 하나 dispacher도 있음
//				mav.setViewName("redirect:"+action);
//			}else {
//				mav.setViewName("redirect:/main.do");
//			}
			
			HttpSession session = request.getSession(); //없으면 생성 있으면 가져옴
			session.invalidate();
			session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			/* MemberVO memInfo=memberService.memberInfo(memberVO); */
			HashMap<String,Object> memInfo=memberService.memberInfo(memberVO);
			if(hos_chk) {
				session.setAttribute("isHos", true);
				session.setAttribute("hos_code", memInfo.get("hos_code"));
				session.setAttribute("hos_name", memInfo.get("hos_name"));
				rAttr.addAttribute("result", "loginSuccess");
				mav.setViewName("redirect:/main.do");
			}else {
				session.setAttribute("user_code", memInfo.get("user_code"));
				session.setAttribute("isHos", false);
				rAttr.addAttribute("result", "loginSuccess");
				mav.setViewName("redirect:/main.do");
			}
			
		}else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}


	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
//		session.removeAttribute("isLogOn");
		session.invalidate();
		ModelAndView mav= new ModelAndView();
		rAttr.addAttribute("result", "logOut");
		mav.setViewName("redirect:/main.do");
		return mav;
	}



}
