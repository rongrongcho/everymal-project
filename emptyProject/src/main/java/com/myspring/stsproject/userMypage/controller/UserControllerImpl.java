package com.myspring.stsproject.userMypage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	
	
	List<UserVO> reviewList=new ArrayList<UserVO>();
	List<UserVO> qList=new ArrayList<UserVO>();
	
	
	@Override
	@RequestMapping(value = "/user_Page/isValidPwd.do", method = RequestMethod.GET)
	public ModelAndView isValidPwd(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		String user_id=request.getParameter("user_id");
		userVO.setUser_id(user_id);
        userVO=userService.calluserInfo(user_id);		
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
	        userVO=userService.calluserInfo(_user_id); 
	        request.setAttribute("userVO", userVO);
	        mav.setViewName("redirect:/user_Page/myInfo.do?user_id=" + user_id);

		}else {
			PrintWriter out=response.getWriter();
			out.print("<script>alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');window.history.back();</script>");
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
        System.out.println("일반회원 프사 파일명:" + userVO.getUser_imgName());
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("userVO", userVO);
		return mav;
	}


	@Override
	@RequestMapping(value = "/user_Page/modInfo.do", method = RequestMethod.POST)
	public ModelAndView modInfo(@ModelAttribute("userVO") UserVO userVO,@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String user_id= (String)session.getAttribute("log_id");
		System.out.println(userVO.getUser_addr1());
		String user_tel1 = request.getParameter("usertel_front");
		String user_tel2 = request.getParameter("usertel_end");
		userVO.setUser_tel(user_tel1+user_tel2);
		userVO.setUser_id(user_id);
		System.out.println(userVO.getUser_tel());
		
		// 경로
		  String savePath = "/Users/imnotalong/projctLCR/everymal/src/main/webapp/resources/imgRepo/user_profil/" + user_id;
	    
				
       
		
		// 기존 이미지 파일 삭제 
        File directory = new File(savePath);
        if (directory.exists() && directory.isDirectory()) {
            File[] existingFiles = directory.listFiles();
            for (File existingFile : existingFiles) {
                existingFile.delete();
            }
        }

        // 디렉터리 생성
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // 업로드 처리 
        if (!file.isEmpty()) {
        	String originalFilename = file.getOriginalFilename();
            String saveFilename = savePath + "\\" + originalFilename;
            file.transferTo(new File(saveFilename));
        }
        
        userVO.setUser_imgName(file.getOriginalFilename());
        System.out.println("세팅한 user_imgName>>>>>>:"+userVO.getUser_imgName());
	    
        userService.modUserInfo(userVO);
		ModelAndView mav=new ModelAndView("redirect:/user_Page/myInfo.do?user_id="+user_id);
		return mav;
		
	}


	//프로필 이미지 불러오기 다운로드 보여주기 
	    
		@RequestMapping(value = "/user_Page/searchProfil.do")
	    public ResponseEntity<byte[]> searchProfil(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session=request.getSession();
	    	String user_id = (String)session.getAttribute("log_id");
	        
	        try {
	            String imagePath = userService.searchProfil(user_id);
	            
	            // 이미지 파일 읽어드리기 
	            File imageFile = new File(imagePath);
	            FileInputStream fis = new FileInputStream(imageFile);
	            
	            // 이미지 바이트 배열로 변환 
	            byte[] imageBytes = new byte[(int) imageFile.length()];
	            fis.read(imageBytes);
	            
	            // HTTP 응답으로 이미지 전송 
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG);
	            
	            //jsp 받으려면 필수 (공식임)
	            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            // 예외 발생 시 빈 프로필 사진 디폴트로 띄워줌 
	            //경로
	            String blankImagePath = "/Users/imnotalong/projctLCR/everymal/src/main/webapp/resources/imgblankProfil.png";
	           
	     
	            try {
	                File blankImageFile = new File(blankImagePath);
	                FileInputStream fis = new FileInputStream(blankImageFile);
	                
	                byte[] blankImageBytes = new byte[(int) blankImageFile.length()];
	                fis.read(blankImageBytes);
	                
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.IMAGE_PNG);
	                
	                return new ResponseEntity<>(blankImageBytes, headers, HttpStatus.OK);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                // Try 구문 마저 생성하지 못하면 500 error 반환 
	                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }
	    }


	  //리뷰 목록
		@Override
		@RequestMapping(value = "/user_Page/myReview.do", method = RequestMethod.GET)
		public ModelAndView myReview(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
			String viewName=(String) request.getAttribute("viewName");
			HttpSession session=request.getSession();
			String user_id= (String)session.getAttribute("log_id");
			userVO=userService.calluserInfo(user_id);
			model.addAttribute("userVO", userVO);
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section==null)?"1":_section);
			int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			Map reviewMap=userService.listReviews(pagingMap,user_id);
			reviewMap.put("section", section);
	        reviewMap.put("pageNum", pageNum);
	        model.addAttribute("reviewMap", reviewMap);
	        int review_count=userService.reviewCount(user_id);
	        model.addAttribute("review_count", review_count);
	        ModelAndView mav = new ModelAndView(viewName);
			return mav;
		}


		 //리뷰 상세페이지
		@Override
		@RequestMapping(value = "/user_Page/userViewReview.do", method = RequestMethod.GET)
		public ModelAndView viewReview(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
			String viewName=(String) request.getAttribute("viewName");
			response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
			response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상 
			String rv_code=request.getParameter("rv_code");
			userVO=userService.viewReview(rv_code);
			model.addAttribute("hosReview", userVO);
			ModelAndView mav = new ModelAndView(viewName);
			return mav;
		}


		//리뷰 수정
		@Override
		@RequestMapping(value = "/user_Page/modMyReview.do",method = RequestMethod.POST)
		public ModelAndView modMyReview(Model model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			//리뷰 상세 수정 
			HttpSession session=request.getSession();
			PrintWriter out=response.getWriter();
			response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
			response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상 
			String user_id= (String)session.getAttribute("log_id");
			String rv_code=request.getParameter("rv_code");
			System.out.println("어디갓니? 나의 작고 귀여운 아기 코드 " + rv_code);
			String rv_title=request.getParameter("rv_title");				
			String rv_text=request.getParameter("rv_text");
			String rv_rate=request.getParameter("rv_rate");
		
			userVO.setUser_id(user_id);
			userVO.setRv_code(rv_code);
			userVO.setRv_title(rv_title);
			userVO.setRv_text(rv_text);
			userVO.setRv_rate(Integer.parseInt(rv_rate));
			userService.modUserMyReview(userVO);
		    out = response.getWriter();
		    if(session.getAttribute("log_id")!=null) {
		    out.print("<script>");
		    out.print("alert('리뷰를 수정했습니다.');");
		    out.print("location.href='" + request.getContextPath() + "/user_Page/userViewReview.do?rv_code="+rv_code+"'");
		    out.print("</script>");
		    out.flush();}
		    return null;
		}


		//상세페이지에서 목록으로 돌아가기
		@Override
		@RequestMapping(value = "/user_Page/returnMyReview.do", method = RequestMethod.POST)
		public ModelAndView returnMyReview(Model model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			PrintWriter out=response.getWriter();
			out.print("<script>");
		    out.print("location.href='" + request.getContextPath() + "/user_Page/myReview.do'");
		    out.print("</script>");
		    
			return null;
		}


		//내 질문 목록
		@Override
		@RequestMapping(value = "/user_Page/myQuestion.do", method = RequestMethod.GET)
		public ModelAndView myQuestion(Model model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String viewName=(String) request.getAttribute("viewName");
			response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
			response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상
			HttpSession session=request.getSession();
			String user_id= (String)session.getAttribute("log_id");
			userVO=userService.calluserInfo(user_id);
			model.addAttribute("userVO", userVO);
			String user_code=(String)session.getAttribute("user_code");
			String _section=request.getParameter("section");
	        String _pageNum=request.getParameter("pageNum");
	        int section=Integer.parseInt((_section==null)?"1":_section); 
	        int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
	        Map<String, Integer> pagingMap=new HashMap<String, Integer>(); 
	        pagingMap.put("section", section);
	        pagingMap.put("pageNum", pageNum);
	        Map qMap=userService.listQuestions(pagingMap,user_code);
	        qMap.put("section", section);
	        qMap.put("pageNum", pageNum);
	        model.addAttribute("qMap", qMap);
	        int q_count=userService.qCount(user_code);
	        model.addAttribute("q_count", q_count);
	        ModelAndView mav = new ModelAndView(viewName);
			return mav;
		}


		//리뷰 삭제
		@Override
		@RequestMapping(value = "/user_Page/myRevDel.do", method = RequestMethod.POST)
		public ModelAndView myRevDel(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
					 
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			String[] items=request.getParameterValues("del_chk");
			if(items==null){
				out.print("<script>");
				out.print("alert('삭제할 리뷰를 선택해주세요!');");
				out.print("location.href='" +request.getContextPath()+ "/user_Page/myReview.do" + "';");
				out.print("</script>");
				out.flush();
				
			}else {
				
				for(int i=0; i<items.length; i++) {
	    			System.out.println( "items : " + items[i]);
	    		}
				userService.reqDelReview(items);
				out.print("<script>");
				out.print("alert('삭제 성공!');");
				out.print("location.href='" +request.getContextPath() + "/user_Page/myReview.do" + "';");
				out.print("</script>");
				out.flush();
				
			}
			return null;
		}


		//내 질문 삭제
		@Override
		@RequestMapping(value = "/user_Page/myQusDel.do", method = RequestMethod.POST)
		public ModelAndView myQusDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			String[] items=request.getParameterValues("del_chk");
			if(items==null){
				out.print("<script>");
				out.print("alert('삭제할 질문글을 선택해주세요!');");
				out.print("location.href='" +request.getContextPath()+ "/user_Page/myQuestion.do" + "';");
				out.print("</script>");
				out.flush();
			}else {
				
				for(int i=0; i<items.length; i++) {
	    			System.out.println( "items : " + items[i]);
	    		}
				userService.reqDelQuestion(items);
				out.print("<script>");
				out.print("alert('삭제 성공!');");
				out.print("location.href='" +request.getContextPath() + "/user_Page/myQuestion.do" + "';");
				out.print("</script>");
				out.flush();
			}
			return null;
		}

	
}
