package com.myspring.stsproject.hosList.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myspring.stsproject.hosList.service.hospitalService;
import com.myspring.stsproject.hosList.vo.hospitalVO;
import com.myspring.stsproject.hosReviewInfo.dao.HosReviewDAO;
import com.myspring.stsproject.hosReviewInfo.service.HosReviewService;
import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

@Controller("hospitalController")
public class hospitalControllerImpl extends MultiActionController implements hospitalController{
	@Autowired
	private HosReviewService hosReviewService;
	
	@Autowired
	private hospitalService hospitalService;
	
	@Autowired
	private HosReviewDAO hosReviewDAO;
	
	@Autowired
	private ReviewVO reviewVO;
	
	@Autowired
	private hospitalVO hospitalVO;

	@Override
	@RequestMapping(value = "/hos_List/Hospital_detail.do", method = RequestMethod.GET)
	public ModelAndView Hospital_detail(@ModelAttribute("hospitalVO") hospitalVO hospitalVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		request.setCharacterEncoding("utf-8");
	    HttpSession session=request.getSession();
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    String hos_code=request.getParameter("hos_code");
	    hos_code="hos0001";
	    //hospitalVO result = hospitalDAO.selectHos(hos_code);
	    List<ReviewVO> hosReviewList=new ArrayList();
	    int review_count=hosReviewDAO.reviewcount(hos_code);// (hosReview��Ű�� �̿�)
	    hosReviewList = hosReviewService.listRev2(hos_code); //���� ���� �������� (hosReview��Ű�� �̿�)
	    float review_avg_result=hosReviewService.reviewAvg(hos_code); //�������ϱ� (hosReview��Ű�� �̿�)
	    String review_avg=String.format("%.2f", review_avg_result); //���� �Ҽ� 2�ڸ����� ��� 
	    HashMap<String,Object> hosJoin = hospitalService.hosJoin(hos_code);
	    HashMap<String,Object> hosInfo = hospitalService.hosInfo(hos_code);
	    String tel = (String) hosJoin.get("HOS_TEL");
	    String ftel=tel.substring(tel.length()-4, tel.length());
	    String btel=tel.substring(tel.length()-7, tel.length()-4);
	    String ltel=tel.substring(0, tel.length()-7);
	    tel=ltel+"-"+btel+"-"+ftel;
	    System.out.println(review_avg);
	    System.out.println(review_count);
	    ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("hosReviewList", hosReviewList); 
		mav.addObject("review_count", review_count);
		mav.addObject("review_avg", review_avg);
		mav.addObject("hos_tel", tel);
		mav.addObject("hos_24", hosInfo.get("HOS_24"));
		mav.addObject("hos_365", hosInfo.get("HOS_365"));
		mav.addObject("hos_time_s", hosInfo.get("HOS_TIME_S"));
		mav.addObject("hos_time_e", hosInfo.get("HOS_TIME_E"));
		
		mav.addObject("hos_gs", hosInfo.get("HOS_GS"));
		mav.addObject("hos_im", hosInfo.get("HOS_IM"));
		mav.addObject("hos_em", hosInfo.get("HOS_EM"));
		mav.addObject("hos_rm", hosInfo.get("HOS_RM"));
		mav.addObject("hos_vac", hosInfo.get("HOS_VAC"));
		
		mav.addObject("hos_dog", hosInfo.get("HOS_DOG"));
		mav.addObject("hos_cat", hosInfo.get("HOS_CAT"));
		mav.addObject("hos_small", hosInfo.get("HOS_SMALL"));
		mav.addObject("hos_fish", hosInfo.get("HOS_FISH"));
		mav.addObject("hos_bird", hosInfo.get("HOS_BIRD"));
		mav.addObject("hos_rep", hosInfo.get("HOS_REP"));
		mav.addObject("hos_etc", hosInfo.get("HOS_ETC"));
		
		mav.addObject("hos_intro", hosJoin.get("HOS_INTRO"));
		mav.addObject("hos_addr1", hosJoin.get("HOS_ADDR1"));
		mav.addObject("hos_addr2", hosJoin.get("HOS_ADDR2"));
		mav.addObject("hos_name", hosJoin.get("HOS_NAME"));
		mav.addObject("hos_code", hosJoin.get("HOS_CODE"));
		
		return mav;

	}
	
}
