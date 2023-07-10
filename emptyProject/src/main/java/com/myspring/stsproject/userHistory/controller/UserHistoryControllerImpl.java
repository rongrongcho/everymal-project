package com.myspring.stsproject.userHistory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.hosmypage.dao.HosMypageInfoDAO;
import com.myspring.stsproject.hosmypage.service.HosMypageInfoService;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;
import com.myspring.stsproject.userHistory.service.UserHistoryService;
import com.myspring.stsproject.userHistory.vo.UserHistoryVO;
import com.myspring.stsproject.userMypage.service.UserService;
import com.myspring.stsproject.userMypage.vo.UserVO;

@Controller("userHistoryController")

public class UserHistoryControllerImpl implements UserHistoryController {

	@Autowired
	private  UserHistoryVO userHistoryVO;
	@Autowired
	private  UserVO userVO;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  UserHistoryService userHistoryService;
	
	
	@Autowired
	private HosMypageInfoService hosmypageinfoService;
	
	@Autowired
	private HosMypageInfoVO hosmypageinfoVO;
	
	@Autowired
	private HosMypageInfoDAO hosmypageinfoDAO;
	
	
	
	
	@Override
	@RequestMapping("/user_History/myHosReserv.do")
	public ModelAndView callMyHosReserv(HosMypageInfoVO hosmypageinfoVO, String hos_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    HttpSession session=request.getSession();
        String log_id=(String)session.getAttribute("log_id");
        userHistoryVO.setUser_id(log_id);
        System.out.println("UserHistoryController -> " + log_id);
        System.out.println("UserHistoryController -> " + userHistoryVO.getUser_id());
        
		String viewName=(String) request.getAttribute("viewName");
		List<UserHistoryVO> historyList=new ArrayList<UserHistoryVO>(); 
        List<UserHistoryVO> recentReserv=new ArrayList<UserHistoryVO>();


        historyList=userHistoryService.listHReserv(userHistoryVO); // 지난 이용 내역 데이터 
        recentReserv=userHistoryService.listHRecent(userHistoryVO);//가장 최근 예약 데이터 
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("historyList", historyList);
		mav.addObject("recentList", recentReserv); 
		return mav;
	}

	@Override
	@RequestMapping("/user_History/userResView.do")
	public ModelAndView viewMyHosReserv(HosMypageInfoVO hosmypageinfoVO, String hos_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");		
	    String res_code=request.getParameter("res_code");
        userHistoryVO=userHistoryService.vHis(res_code);
		String viewName=(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userVO",userVO);
		mav.addObject("viewHis",userHistoryVO);
		return mav;
	}

	@Override
	@RequestMapping("/user_History/myPetTaxiReserv.do")
	public ModelAndView callMyTaxiReserv(HosMypageInfoVO hosmypageinfoVO, String hos_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    HttpSession session=request.getSession();
		String viewName=(String) request.getAttribute("viewName");
        String log_id=(String)session.getAttribute("log_id");
		//String user_code=(String)session.getAttribute("user_code");
		String user_code="use0001";
        System.out.println("UserHistorycontroller 로그인 아이디 : " + log_id+" user_code : "+ user_code);
		userVO=userService.calluserInfo(log_id); //회원 정보 불러옴
		userHistoryVO.setUser_id(log_id);
		userHistoryVO.setUser_code(user_code);
	    List<UserHistoryVO> historyList=new ArrayList<UserHistoryVO>(); 
        List<UserHistoryVO> recentReserv=new ArrayList<UserHistoryVO>();
        historyList=userHistoryService.listTxReserv(userHistoryVO); // 지난 이용 내역 데이터 
        recentReserv=userHistoryService.listTxRecent(userHistoryVO);//가장 최근 예약 데이터 
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("taxiHistory", historyList);
		mav.addObject("taxiRecent", recentReserv);
		return mav;
	}

	@Override
	@RequestMapping("/user_History/userResTaxiView.do")
	public ModelAndView viewMyTaxiReserv(HosMypageInfoVO hosmypageinfoVO, String hos_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");	
		String viewName=(String) request.getAttribute("viewName");
		String tx_res_code=request.getParameter("tx_res_code");
		userHistoryVO=userHistoryService.vTaxiHis(tx_res_code);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("viewHis", userHistoryVO);
		return mav;
	}

}
