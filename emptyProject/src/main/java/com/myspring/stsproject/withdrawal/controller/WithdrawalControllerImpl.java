package com.myspring.stsproject.withdrawal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myspring.stsproject.withdrawal.dao.WithdrawalDAO;
import com.myspring.stsproject.withdrawal.vo.WithdrawalVO;

@Controller("withdrawalController")
public class WithdrawalControllerImpl extends MultiActionController implements WithdrawalController {
	
	@Autowired
	private WithdrawalVO withdrawalVO;
	
	@Autowired
	private WithdrawalDAO withdrawalDAO;
	
	@RequestMapping(value = "/member/withdrawal.do", method = RequestMethod.GET)
	private ModelAndView withdrawal(HttpServletRequest request, HttpServletResponse response) {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/updateEnabled.do")
	public ModelAndView updateEnabled(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("log_id");
		String code=(String)session.getAttribute("hos_code");
		System.out.println("id;;;;;;;;"+id);
		withdrawalDAO.updateEnabled(id,code);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
}






	



