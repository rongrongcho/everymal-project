package com.myspring.stsproject.hosReservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.hosList.service.hospitalService;
import com.myspring.stsproject.hosList.service.hospitalServiceImpl;
import com.myspring.stsproject.hosReservation.dao.ReservationDAO;
import com.myspring.stsproject.hosReservation.vo.ReservationVO;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

@Controller("reservationController")
public class ReservationControllerImpl extends MultiActionController implements ReservationController {
	
	@Autowired
	private ReservationVO reservationVO;
	
	@Autowired
	private ReservationDAO reservationDAO;
	
	@Autowired
	private HosMypageInfoVO hosmypageinfoVO;
	
	@Autowired
	private hospitalService hospitalService;
	
	
	@RequestMapping(value = "/hos_Reservation/reservationForm.do", method = RequestMethod.GET)
	private ModelAndView reservationForm(HttpServletRequest request, HttpServletResponse response) {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/hos_Reservation/listPopup.do")
	private ModelAndView listPopup(@ModelAttribute("reservationVO") ReservationVO reservationVO, Model model, RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) {
		String viewName=(String)request.getAttribute("viewName");
		HttpSession session=request.getSession();
		List<ReservationVO> animalList=new ArrayList<ReservationVO>();
		String user_code=(String)session.getAttribute("user_code");
		animalList=reservationDAO.selectPetinfo(user_code);
		model.addAttribute("animalList",animalList);
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/hos_Reservation/reservation.do")
	private ModelAndView reservation(@ModelAttribute("reservationVO") ReservationVO reservationVO, Model model, RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String viewName=(String)request.getAttribute("viewName");
		response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상 
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		String user_id=(String)session.getAttribute("log_id");
		String user_code=(String)session.getAttribute("user_code");
		//String hos_code=(String)session.getAttribute("hos_code");
		//String hos_code="hos9999";
		
		if(user_code==null) {
			out.println("<script>");
            out.println("alert('예약을 하시려면 로그인을 먼저 해주세요.');");
            out.println("history.back();"); // 이전 페이지로 이동
            out.println("</script>");
            out.flush();
            return null;
		}
		String hos_code=request.getParameter("hos_code");
		String tel_front=request.getParameter("tel_front");
        String tel_end=request.getParameter("tel_end");
        String user_tel=tel_front+tel_end;
        String user_name=request.getParameter("user_name");
		//String hos_name=request.getParameter("hos_name");
        String hos_name=request.getParameter("hos_name");
        String pet_name=request.getParameter("pet_name");
        String pet_code=request.getParameter("pet_code");
        String pet_number=request.getParameter("pet_number");
        String pet_sex=request.getParameter("pet_sex");
        String pet_types=request.getParameter("pet_types");
        String b_type=request.getParameter("b_type");
        String res_date=request.getParameter("res_date");
        String res_time=request.getParameter("res_time");
        String res_etc=request.getParameter("res_etc");
        int pet_age=Integer.parseInt(request.getParameter("pet_age"));
        float pet_weight=Float.parseFloat(request.getParameter("pet_weight"));
        String [] hosSub=request.getParameterValues("hos_sub");
        String hos_sub=String.join(",", hosSub);
        //////
        reservationVO.setUser_tel(user_tel);
        reservationVO.setUser_id(user_id);
        reservationVO.setHos_code(hos_code);
        reservationVO.setUser_code(user_code);
        reservationVO.setUser_name(user_name);
        reservationVO.setPet_name(pet_name);
        reservationVO.setPet_code(pet_code);
        reservationVO.setPet_number(pet_number);
        reservationVO.setPet_sex(pet_sex);
        reservationVO.setPet_types(pet_types);
        reservationVO.setPet_age(pet_age);
        reservationVO.setRes_code(reservationDAO.resNumber());
        reservationVO.setB_type(b_type);
        reservationVO.setRes_date(res_date);
        reservationVO.setRes_etc(res_etc);
        reservationVO.setPet_weight(pet_weight);
        reservationVO.setHos_name(hos_name);
        reservationVO.setHos_sub(hos_sub);
        reservationVO.setRes_time(res_time);
        reservationDAO.insertReservation(reservationVO);
        
        out=response.getWriter();
        out.print("<script>");
        out.print("alert('예약을 완료했습니다.'); ");
        out.print("location.href='" +request.getContextPath() + "/main.do';"); //내 정보에서 예약페이지 확인으로 넘어가도록 
        out.print("</script>");
        out.flush();
        return null;
	}

}






	



