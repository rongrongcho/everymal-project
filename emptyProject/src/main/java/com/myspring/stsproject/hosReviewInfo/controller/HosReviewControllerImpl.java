package com.myspring.stsproject.hosReviewInfo.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.forImg.dao.HosImgDAO;
import com.myspring.stsproject.forImg.vo.HosImgVO;
import com.myspring.stsproject.hosReviewInfo.service.HosReviewService;
import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;
import com.myspring.stsproject.hosmypage.service.HosMypageInfoService;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

@Controller("hosReviewController") 
public class HosReviewControllerImpl implements HosReviewController {

	@Autowired
	private HosReviewService hosReviewService;

	@Autowired
	private ReviewVO reviewVO;
	
	@Autowired
	HosMypageInfoVO hosMypageInfoVO;
	
	@Autowired
	HosMypageInfoService hosmypageinfoService;
	
	@Autowired
	HosImgVO hosImgVO;
	
	@Autowired
	HosImgDAO hosImgDAO;
	
	
	
	@Override
	@RequestMapping(value = "/hos_ReviewInfo/hosReviewList.do", method = RequestMethod.GET)
	public ModelAndView hosReviewList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		String hos_id=(String)session.getAttribute("log_id");
		String hos_code=(String)session.getAttribute("hos_code");
		hos_code="hos0001";
		hos_id="kkm1234";
		model.addAttribute("hos_code",hos_code);
		//이미지
		hosImgVO=hosImgDAO.getHosPro(hos_code);
		//hos_pro 담아옴
		//
		System.out.println("병원코드 불러왔나?>>>>"+ hos_code);
		System.out.println("병원아이디 불러왔나?>>>>"+ hos_id);
		hosMypageInfoVO =hosmypageinfoService.selecthosInfo(hos_id);
        
		float review_avg_result=hosReviewService.reviewAvg(hos_code);
		String review_avg=String.format("%.2f", review_avg_result);
		String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section=Integer.parseInt((_section==null)?"1":_section);
		int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map hosReviewMap=hosReviewService.listRev(pagingMap, hos_code);
		hosReviewMap.put("section", section);
		hosReviewMap.put("pageNum", pageNum);
		//List<ReviewVO> hosReviewList = hosReviewService.listRev2(hos_code);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("hosReviewMap", hosReviewMap); 
		mav.addObject("review_avg", review_avg);
		mav.addObject("hosMypageInfoVO", hosMypageInfoVO);
		mav.addObject("hosImgVO", hosImgVO);
		//mav.addObject("hosReviewList", hosReviewList); 
		return mav;
	}

	@Override
	@RequestMapping(value = "/hos_ReviewInfo/hosRevDel.do", method = RequestMethod.POST)
	public ModelAndView hosRevDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		 
		response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상 
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String hos_id=(String)session.getAttribute("log_id");
		String[] items=null;
        items=request.getParameterValues("del_chk");
        ModelAndView mav = new ModelAndView(viewName);
        
		
		if(items==null){
			out.print("<script>");
			out.print("alert('삭제요청 할 리뷰를 선택해주세요!');");
			out.print("location.href='" +request.getContextPath()+ "/hos_ReviewInfo/hosReviewList.do" + "';");			
			out.print("</script>");			
			out.flush();	
			 return null;
		}else {
			
			for(int i=0; i<items.length; i++) {
    			System.out.println( "items : " + items[i]);
    		}
			hosReviewService.reqDelReview(items);
			out.print("<script>");
			out.print("alert('삭제요청 성공!');");
			out.print("location.href='" +request.getContextPath() + "/hos_ReviewInfo/hosReviewList.do" + "';");
			out.print("</script>");
			out.flush();
			ModelAndView redirectMav = new ModelAndView("redirect:/hos_ReviewInfo/hosReviewList.do");
			return redirectMav;
		}
		 
		 
		//mav.setViewName("redirect:/hosReview/hosReviewList.do");		   
	    //return mav;
	}

	
	
	@Override
	@RequestMapping(value = "/hos_ReviewInfo/hosviewReview.do", method = RequestMethod.GET)
	public ModelAndView viewReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String hos_id=(String)session.getAttribute("log_id");
    	String rv_code=request.getParameter("rv_code");
    	String viewName=(String) request.getAttribute("viewName");
    	ModelAndView mav = new ModelAndView(viewName);
    	reviewVO=hosReviewService.viewReview(rv_code);
    	mav.addObject("hosReview", reviewVO); 
		return mav;
	}

	
	
	
}
