package com.myspring.stsproject.petTaxi.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.petTaxi.service.PetTaxiService;
import com.myspring.stsproject.petTaxi.vo.PetTaxiVO;



@Controller("petTaxiController")
public class PetTaxiControllerImpl implements PetTaxiController {
	
	@Autowired
	private PetTaxiVO petTaxiVO;
	@Autowired
	private PetTaxiService petTaxiService;

	@Override
	@RequestMapping(value = "/pet_Taxi/petTaxiPage.do")
	public ModelAndView openPetTaxiPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8"); 
		String viewName=(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value = "/pet_Taxi/taxiList.do")
	public ModelAndView goToTaxiSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8"); 
		String viewName=(String) request.getAttribute("viewName");
		String tx_local = request.getParameter("tx_local");
        List<PetTaxiVO> taxiList = new ArrayList<PetTaxiVO>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("petTaxiVO", petTaxiVO);
        paramMap.put("tx_local", tx_local);
        taxiList = petTaxiService.listTaxi(paramMap);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("taxiList",taxiList);
		return mav;
	}

	@Override
	@RequestMapping(value = "/pet_Taxi/reservPetTaxi.do")

	public ModelAndView ReservPetTaxi(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8"); 
		String viewName=(String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		String user_id=(String)session.getAttribute("log_id");
		PrintWriter out;
		System.out.println("========"+petTaxiVO.getUser_code());
        if(user_id==null) {
 
    	    out=response.getWriter();
		    out.print("<script>");
			out.print("alert('로그인후 이용해주세요!'); ");
			out.print("location.href='" +request.getContextPath() + "/pet_Taxi/petTaxiPage.do';");
		    out.print("</script>");
		    out.flush();
		    return null;  	
        }else {
        	String user_code=request.getParameter("user_code");
    		String tx_tel=request.getParameter("tx_tel");
    		String tx_number=request.getParameter("tx_number");
    		String tx_name=request.getParameter("tx_name");
    		String tx_dep=request.getParameter("tx_dep");
    		String tx_arr=request.getParameter("tx_arr");
    		String res_name=request.getParameter("res_name");
    		String tx_code=request.getParameter("tx_code");            		
    		String tx_restime=request.getParameter("tx_restime");
    		String tx_reserv_day=request.getParameter("tx_reserv_day");
    	    petTaxiVO.setUser_code(user_code);
    	    petTaxiVO.setTx_tel(tx_tel);
    	    petTaxiVO.setTx_number(tx_number);
    	    petTaxiVO.setTx_name(tx_name);
    	    petTaxiVO.setTx_dep(tx_dep);
    	    petTaxiVO.setTx_arr(tx_arr);
    	    petTaxiVO.setRes_name(res_name);
    	    petTaxiVO.setTx_code(tx_code);
    	    petTaxiVO.setTx_restime(tx_restime);
    	    petTaxiVO.setTx_reserv_day(tx_reserv_day);
    	    petTaxiService.taxiReserv(petTaxiVO);
    	    out=response.getWriter();
		    out.print("<script>");
			out.print("alert('성공적으로 펫 택시 예약이 완료되었습니다. '); ");
			out.print("location.href='" +request.getContextPath() + "/pet_Taxi/petTaxiPage.do';");
		    out.print("</script>");
		    out.flush();	
 			ModelAndView redirectMav = new ModelAndView("redirect:/pet_Taxi/petTaxiPage.do");
			return redirectMav;
        }

	}

}
