package com.myspring.stsproject.admin.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.stsproject.admin.dao.AdminDAO;
import com.myspring.stsproject.admin.service.AdminService;
import com.myspring.stsproject.admin.vo.AdminVO;
import com.myspring.stsproject.hosReviewInfo.service.HosReviewService;
import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;

@Controller("adminController")

public class AdminControllerImpl implements AdminController {

	@Autowired
	private AdminVO adminVO;
	
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HosReviewService hosReviewService;

	@Autowired
	private ReviewVO reviewVO;

	
	
	@Override
	@RequestMapping(value = "/administrator/reqDelReviewList.do", method = RequestMethod.GET)
	public ModelAndView reqDelReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rv_delreq=1;
		String viewName=(String) request.getAttribute("viewName");
	    String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section=Integer.parseInt((_section==null)?"1":_section);
		int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
		Map pagingMap=new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map reqDelhosReviewMap=hosReviewService.allReqDel(pagingMap, rv_delreq);
		reqDelhosReviewMap.put("section", section);
		reqDelhosReviewMap.put("pageNum", pageNum);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("reqDelhosReviewMap",reqDelhosReviewMap);
		return mav;
	}


	@Override
	@RequestMapping(value = "/administrator/reviewDelete.do", method = RequestMethod.POST)
	public ModelAndView reviewDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");		 
		response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		PrintWriter out = response.getWriter();		  
		response.setContentType("text/html;charset=UTF-8");
			String[] items=null;
         items=request.getParameterValues("del_chk");
         ModelAndView mav = new ModelAndView(viewName);
 		if(items==null){
 			out.print("<script>");
 			out.print("alert('삭제할 리뷰를 선택해주세요!');");
 			out.print("location.href='" +request.getContextPath()+ "/administrator/reqDelReviewList.do" + "';");
 			out.print("</script>");
 			out.flush();	
			 return null;
 		}else {
 			
 			for(int i=0; i<items.length; i++) {
     			System.out.println( "items : " + items[i]);
     		}
 			hosReviewService.deleteReveiws(items); //여기 함
 			out.print("<script>");
 			out.print("alert('리뷰 영구 삭제 성공!');");
 			out.print("location.href='" +request.getContextPath() + "/administrator/reqDelReviewList.do" + "';");
 			out.print("</script>");
 			out.flush();	
 			ModelAndView redirectMav = new ModelAndView("redirect:/administrator/reqDelReviewList.do");
			return redirectMav;
 		}
	}
	
	
	
	
	
	//병원 등록 신청 목록 조회 페이지 + 페이징 뷰 
	
	@RequestMapping(value="/administrator/hopitalRegi.do")
	public ModelAndView hosRMList(HttpServletRequest request, Model model) throws Exception {

		String viewName=(String) request.getAttribute("viewName");
		String orderBy = request.getParameter("orderby");
		System.out.println("안녕"+orderBy);
	    String _section=request.getParameter("section");
        String _pageNum=request.getParameter("pageNum");
        int section=Integer.parseInt((_section==null)?"1":_section); 
        int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
        Map<String, Object> pagingMap = new HashMap<String, Object>(); // 섹션,pageNum,orderBy 만 삽입되어 있음. 
        pagingMap.put("section", section);
        pagingMap.put("pageNum", pageNum);
	    pagingMap.put("orderby", orderBy);
	    Map<String, Object> rmResult=adminService.listApps(pagingMap);
	    List rmList=(List)rmResult.get("rmList");
	    int totalApps = (int) rmResult.get("totalApps");
	    
	    model.addAttribute("rmList",rmList);
	    model.addAttribute("totalApps", totalApps);
//        Map adminMap=adminService.listApps(pagingMap);
//        adminMap.put("section", section);
//        adminMap.put("pageNum", pageNum);
        
//	    Map<String, Object> result = adminService.memberList(pagingMap); //
//	    List userList = (List) result.get("userList");
//	    int totalList = (int) result.get("totalList");
//        int app_count=adminService.appCount();
//        model.addAttribute("userList", userList); //${ds}

	    
		ModelAndView mav = new ModelAndView(viewName);
	    mav.addObject("rmList", rmList);

        return mav;
	}

	@Override
	@RequestMapping(value = "/administrator/approvalHosM.do", method = RequestMethod.POST)
	public ModelAndView approvalHosM(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8"); // alert한글 깨짐 현상
	    String viewName = (String) request.getAttribute("viewName");
	    PrintWriter out = response.getWriter();
	    String[] chkArray=null;
	     chkArray = request.getParameterValues("rm_chk");
	    if (chkArray == null || chkArray.length == 0) {
	        out.print("<script>");
	        out.print("alert('등록 승인 할 병원을 선택해주세요!');");
	        out.print("location.href='" + request.getContextPath() + "/administrator/hopitalRegi.do" + "';");
	        out.print("</script>");
	        out.flush();
	        return null;
	    } else {
	        adminService.approvalHosM(chkArray); // 배열 그대로 전달
	        out.print("<script>");
	        out.print("alert('병원 등록 성공!');");
	        out.print("location.href='" + request.getContextPath() + "/administrator/hopitalRegi.do" + "';");
	        out.print("</script>");
	        out.flush();
	        return new ModelAndView("redirect:/administrator/hopitalRegi.do");
	    }
	}
	
	
	@RequestMapping(value = "/administrator/memberList.do")
	public ModelAndView handleSelectedValue(HttpServletRequest request, Model model) throws Exception {
	    String viewName = (String) request.getAttribute("viewName");
	    String orderBy = request.getParameter("orderby");// 받았어 , gList 인지 hList인지 
	    System.out.println(orderBy);
	    String _section = request.getParameter("section");
	    String _pageNum = request.getParameter("pageNum");
	    int section = Integer.parseInt((_section == null) ? "1" : _section);
	    int pageNum = Integer.parseInt((_pageNum == null) ? "1" : _pageNum);

	    Map<String, Object> pagingMap = new HashMap<String, Object>(); // 섹션,pageNum,orderBy 만 삽입되어 있음. 
	    pagingMap.put("section", section);
	    pagingMap.put("pageNum", pageNum);
	    pagingMap.put("orderby", orderBy);
	    System.out.println("section:::"+section);
	    System.out.println("pageNum:::"+pageNum);

	    Map<String, Object> result = adminService.memberList(pagingMap); //
	    List userList = (List) result.get("userList");
	    int totalList = (int) result.get("totalList");
	    //System.out.println(result.values());
	    System.out.println(totalList);

	    model.addAttribute("userList", userList); //${ds}
	    model.addAttribute("totalList", totalList);
	    
	    ModelAndView mav = new ModelAndView(viewName); // jsp 통째로 가져오는 거야 
	    mav.addObject("userList", userList);
	    
	    return mav;
	}

//=========================================================================
	@Override
	@RequestMapping(value = "administrator/adminApplication.do")
	public ModelAndView checkApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자의 병원등록 신청 개별 상세 보기
		String viewName=(String) request.getAttribute("viewName");
		String hos_code=request.getParameter("hos_code");
		adminDAO.checkHos(hos_code);
		adminVO=adminService.viewApplication(hos_code);
		System.out.println(adminVO.getHos_status());
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("appVO", adminVO); 
		return mav;
	}


	@Override
	@RequestMapping(value = "adminstrator/approval.do")
	public ModelAndView approval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		PrintWriter out = response.getWriter();		  
		response.setContentType("text/html;charset=UTF-8");
		String viewName=(String) request.getAttribute("viewName");
		String hos_code=request.getParameter("hos_code");
		adminDAO.approvalRM(hos_code);
		out.print("<script>");
        out.print("alert('병원 등록 성공!');");
        out.print("location.href='" + request.getContextPath() + "/administrator/hopitalRegi.do" + "';");
        out.print("</script>");
        out.flush();
        return new ModelAndView("redirect:administrator/adminApplication.do");
		
		
	}


	@Override
	@RequestMapping(value="/adminstrator/rjRM.do")
	public ModelAndView rjRM(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		PrintWriter out = response.getWriter();		  
		response.setContentType("text/html;charset=UTF-8");
		String viewName=(String) request.getAttribute("viewName");
		String hos_code=request.getParameter("hos_code");
		String rm_say=request.getParameter("rm_say");
		Map rjMap=new HashMap<String, String>();
		rjMap.put("hos_code", hos_code);
		if(rm_say == null || rm_say == "") {
			out.print("<script>");
	        out.print("alert('거절 사유를 입력해주세요 ');");
	        
	        out.print("location.href='" + request.getContextPath() + "/administrator/adminApplication.do?hos_code="+hos_code+"';");
	        out.print("</script>");
	        out.flush();
	        return null;
		}else{
			rjMap.put("rm_say", rm_say);
			adminDAO.rjRM(rjMap);
			out.print("<script>");
	        out.print("alert('신청을 성공적으로 거절하였습니다.');");
	        out.print("location.href='" + request.getContextPath() + "/administrator/hopitalRegi.do" + "';");
	        out.print("</script>");
	        out.flush();
	        return new ModelAndView("redirect:administrator/adminApplication.do");
			
		}
		
	}











}
