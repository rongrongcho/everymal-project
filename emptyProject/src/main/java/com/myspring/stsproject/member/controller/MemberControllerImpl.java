package com.myspring.stsproject.member.controller;

import java.io.PrintWriter;
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
	public void addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String code = memberService.selectUsercode(memberVO);
		int maxNum = Integer.parseInt(code) + 1;
        code = String.format("use%04d", maxNum);
		String user_tel1 = request.getParameter("user_tel1");
		String user_tel2 = request.getParameter("user_tel2");
		String user_tel3 = request.getParameter("user_tel3");
		String user_tel = user_tel1+user_tel2+user_tel3;
		memberVO.setUser_tel(user_tel);
		memberVO.setUser_code(code);
		String userCode = memberService.addMember(memberVO);
		out.print(userCode);
	}
	
	@Override
	@RequestMapping(value = "/member/addHospital.do", method = RequestMethod.POST)
	public void addHospital(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String code = memberService.selectHoscode(memberVO);
		int maxNum = Integer.parseInt(code) + 1;
        code = String.format("hos%04d", maxNum);
		String hos_usertel1 = request.getParameter("hos_usertel1");
        String hos_usertel2 = request.getParameter("hos_usertel2");
        String hos_usertel3 = request.getParameter("hos_usertel3");
        String hos_usertel = hos_usertel1+hos_usertel2+hos_usertel3;
        String hos_dno1 = request.getParameter("hos_dno1");
        String hos_dno2 = request.getParameter("hos_dno2");
        String hos_dno3 = request.getParameter("hos_dno3");
        String hos_dno = hos_dno1+hos_dno2+hos_dno3;
        String hos_tel1 = request.getParameter("hos_tel1");
        String hos_tel2 = request.getParameter("hos_tel2");
        String hos_tel3 = request.getParameter("hos_tel3");
        String hos_tel = hos_tel1+hos_tel2+hos_tel3;
        String hos_365_1 = request.getParameter("hos_365_1");
        String hos_365_2 = request.getParameter("hos_365_2");
        String hos_365_3 = request.getParameter("hos_365_3");
        String hos_365_4 = request.getParameter("hos_365_4");
        String hos_365_5 = request.getParameter("hos_365_5");
        String hos_365_6 = request.getParameter("hos_365_6");
        String hos_365_7 = request.getParameter("hos_365_7");
        String hos_365 = "";
        if(hos_365_1 != null && !hos_365_1.equals("") ){
           hos_365 += hos_365_1;
        }
        if(hos_365_2 != null && !hos_365_2.equals("") ){
           hos_365 += hos_365_2;
        }
        if(hos_365_3 != null && !hos_365_3.equals("") ){
           hos_365 += hos_365_3;
        }
        if(hos_365_4 != null && !hos_365_4.equals("") ){
           hos_365 += hos_365_4;
        }
        if(hos_365_5 != null && !hos_365_5.equals("") ){
           hos_365 += hos_365_5;
        }
        if(hos_365_6 != null && !hos_365_6.equals("") ){
           hos_365 += hos_365_6;
        }
        if(hos_365_7 != null && !hos_365_7.equals("") ){
           hos_365 += hos_365_7;
        }
        String hos_24 = request.getParameter("hos_24");
        String hos_dog = request.getParameter("hos_dog");
        String hos_cat = request.getParameter("hos_cat");
        String hos_small = request.getParameter("hos_small");
        String hos_fish = request.getParameter("hos_fish");
        String hos_bird = request.getParameter("hos_bird");
        String hos_rep = request.getParameter("hos_rep");
        String hos_etc = request.getParameter("hos_etc");
        String hos_gs = request.getParameter("hos_gs");
        String hos_im = request.getParameter("hos_im");
        String hos_em = request.getParameter("hos_em");
        String hos_rm = request.getParameter("hos_rm");
        String hos_vac = request.getParameter("hos_vac");
        if(hos_24 == null){
        	memberVO.setHos_24("0");
         }
        else if(hos_24 != null){
        	memberVO.setHos_24("1");
        }
        if(hos_dog == null){
        	memberVO.setHos_dog("0");
         }
        else if(hos_dog != null){
        	memberVO.setHos_dog("1");
        }
        if(hos_cat == null){
        	memberVO.setHos_cat("0");
         }
        else if(hos_cat != null){
        	memberVO.setHos_cat("1");
        }
        if(hos_small == null){
        	memberVO.setHos_small("0");
         }
        else if(hos_small != null){
        	memberVO.setHos_small("1");
        }
        if(hos_fish == null){
        	memberVO.setHos_fish("0");
         }
        else if(hos_fish != null){
        	memberVO.setHos_fish("1");
        }
        if(hos_bird == null){
        	memberVO.setHos_bird("0");
         }
        else if(hos_bird != null){
        	memberVO.setHos_bird("1");
        }
        if(hos_rep == null){
        	memberVO.setHos_rep("0");
         }
        else if(hos_rep != null){
        	memberVO.setHos_rep("1");
        }
        if(hos_etc == null){
        	memberVO.setHos_etc("0");
         }
        else if(hos_etc != null){
        	memberVO.setHos_etc("1");
        }
        if(hos_gs == null){
        	memberVO.setHos_gs("0");
         }
        else if(hos_gs != null){
        	memberVO.setHos_gs("1");
        }
        if(hos_im == null){
        	memberVO.setHos_im("0");
         }
        else if(hos_im != null){
        	memberVO.setHos_im("1");
        }
        if(hos_em == null){
        	memberVO.setHos_em("0");
         }
        else if(hos_em != null){
        	memberVO.setHos_em("1");
        }
        if(hos_rm == null){
        	memberVO.setHos_rm("0");
         }
        else if(hos_rm != null){
        	memberVO.setHos_rm("1");
        }
        if(hos_vac == null){
        	memberVO.setHos_vac("0");
         }
        else if(hos_vac != null){
        	memberVO.setHos_vac("1");
        }
        
        memberVO.setHos_usertel(hos_usertel);
        memberVO.setHos_dno(hos_dno);
        memberVO.setHos_tel(hos_tel);
        memberVO.setHos_365(hos_365);
        memberVO.setHos_code(code);
        memberService.addHospital(memberVO);
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
		result=memberService.login(memberVO);
		if(result == true) {
			HttpSession session = request.getSession();
			session.invalidate();
			session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			HashMap<String,Object> memInfo=memberService.memberInfo(memberVO);
			if(hos_chk) {
				session.setAttribute("isHos", true);
				session.setAttribute("hos_code", memInfo.get("HOS_CODE"));
				session.setAttribute("hos_name", memInfo.get("HOS_NAME"));
				String value = (String)session.getAttribute("hos_code");
				rAttr.addAttribute("result", "loginSuccess");
				mav.setViewName("redirect:/main.do");
			}else {
				session.setAttribute("user_code", memInfo.get("USER_CODE"));
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
		session.invalidate();
		ModelAndView mav= new ModelAndView();
		rAttr.addAttribute("result", "logOut");
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/searchId.do", method = RequestMethod.POST)
	public void searchId(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
		if(hos_chk) {
			String name=request.getParameter("rname2");
			String hos_dno=request.getParameter("work_no1")+'-'+request.getParameter("work_no2")+'-'+request.getParameter("work_no3");
			memberVO.setName(name);
			memberVO.setHos_dno(hos_dno);
		}else {
			String name=request.getParameter("rname1");
			String email=request.getParameter("ident")+'@'+request.getParameter("ident1");
			memberVO.setName(name);
			memberVO.setEmail(email);
		}
		String id = memberService.searchId(memberVO);
		out.print("<script>");
		if(id==null) {
			out.print("alert('회원정보가 존재하지 않습니다!');");
		}else {
			id=id.substring(0, 3)+"***";
			out.print("alert('회원님의 아이디는 "+id+" 입니다!');");
		}
		out.print("location.href='"+request.getContextPath()+"/member/loginForm.do';");
		out.print("</script>");
	}
	
	@RequestMapping(value = "/member/searchPass.do", method = RequestMethod.POST)
	public void searchPass(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		boolean hos_chk = Boolean.parseBoolean(request.getParameter("chk"));
		if(hos_chk) {
			String name=request.getParameter("rname2");
			String id=request.getParameter("rid2");
			String hos_dno=request.getParameter("work_no1")+request.getParameter("work_no2")+request.getParameter("work_no3");
			memberVO.setHos_chk(hos_chk);
			memberVO.setName(name);
			memberVO.setId(id);
			memberVO.setHos_dno(hos_dno);
		}else {
			String name=request.getParameter("rname1");
			String id=request.getParameter("rid1");
			String email=request.getParameter("ident")+'@'+request.getParameter("ident1");
			memberVO.setHos_chk(hos_chk);
			memberVO.setName(name);
			memberVO.setId(id);
			memberVO.setEmail(email);
		}
		boolean result = false;
		result = memberService.searchPass(memberVO);
		out.print("<script>");
		if(result) {
			out.print("alert('회원님의 이메일로 비밀번호를 재설정 할 수 있는 링크를 보냈습니다!');");
		}else {
			out.print("alert('회원정보가 존재하지 않습니다!');");
		}
		out.print("location.href='"+request.getContextPath()+"/member/loginForm.do';");
		out.print("</script>");
	}
	
	@RequestMapping(value = "/member/idChk.do", method = RequestMethod.POST)
	public void idChk(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		boolean result=false;
		String id = memberService.idChk(memberVO);
		result=Boolean.parseBoolean(id);
		if(result==true) {
			out.print("not_usable");
		}else{
			out.print("usable");
		}
	}
	
	@RequestMapping(value = "/member/HosidChk.do", method = RequestMethod.POST)
	public void HosidChk(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		boolean result=false;
		String id = memberService.HosidChk(memberVO);
		result=Boolean.parseBoolean(id);
		if(result==true) {
			out.print("not_usable");
		}else{
			out.print("usable");
		}
	}


}
