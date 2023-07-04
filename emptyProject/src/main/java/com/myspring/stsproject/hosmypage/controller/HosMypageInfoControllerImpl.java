package com.myspring.stsproject.hosmypage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.hosmypage.dao.HosMypageInfoDAO;
import com.myspring.stsproject.hosmypage.service.HosMypageInfoService;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

@Controller("hosmypageinfoController")
public class HosMypageInfoControllerImpl extends MultiActionController implements HosMypageInfoController {
	
	@Autowired
	private HosMypageInfoService hosmypageinfoService;
	
	@Autowired
	private HosMypageInfoVO hosmypageinfoVO;
	
	@Autowired
	private HosMypageInfoDAO hosmypageinfoDAO;
	
	@RequestMapping(value = "/hos_MypageInfo/hosMypage.do", method = RequestMethod.GET)
	public ModelAndView valid(@ModelAttribute("hosMypage") HosMypageInfoVO hosmypageinfoVO, @RequestParam(value = "action", required = false) String action, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/hos_MypageInfo/hosApplication.do", method = RequestMethod.GET)
	public ModelAndView hosApplication(@ModelAttribute("hosMypage") HosMypageInfoVO hosmypageinfoVO, @RequestParam(value = "action", required = false) String action, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		String _hos_id=(String) session.getAttribute("log_id");
		String hos_code="hos0005";
		HosMypageInfoVO hosInfo=hosmypageinfoService.selecthosInfo(_hos_id);
		Map<String, String> thumbMap = hosmypageinfoDAO.searchThumb(hos_code);
		System.out.println("thumbMap:::::::::"+thumbMap);
	    String himg1 = thumbMap.get("HIMG1");
	    String himg2 = thumbMap.get("HIMG2");
	    String himg3 = thumbMap.get("HIMG3");
	    System.out.println("이미지를 못 받아오는 걸까?" +himg1);
	    hosInfo.setHos_code(hos_code);
	    System.out.println("그렇다면 호스코드?" +hosInfo.getHos_code());
	    hosInfo.setHimg1(himg1);
	    hosInfo.setHimg2(himg2);
	    hosInfo.setHimg3(himg3);
		mav.addObject("hosmypageinfoVO", hosInfo);
		System.out.println(himg1);
		System.out.println(himg2);
		System.out.println(himg3);
		return mav;
	}
	
	@RequestMapping(value = "/hos_MypageInfo/hosUserInformation.do", method = RequestMethod.GET)
	public ModelAndView hosInfo(@ModelAttribute("hosMypage") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id, @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		String _hos_id=(String) session.getAttribute("log_id");
		HosMypageInfoVO hosInfo=hosmypageinfoService.selecthosInfo(_hos_id);
		mav.addObject("hosmypageinfoVO", hosInfo);
		System.out.println("�떎�떆臾쇱뼱蹂몃떎 �궛�궛22 " + mav);
		return mav;
	}
	
	@RequestMapping(value = "/hos_MypageInfo/hosInformation.do", method = RequestMethod.GET)
	public ModelAndView myHosInfo(@ModelAttribute("hosMypage") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id,  @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		String _hos_id=(String) session.getAttribute("log_id");
		HosMypageInfoVO hosInfo=hosmypageinfoService.selecthosInfo(_hos_id);
		mav.addObject("hosmypageinfoVO", hosInfo);
		System.out.println("�떎�떆臾쇱뼱蹂몃떎 �궛�궛33 " + mav);
		return mav;
	}
	
	
	@Override
	@RequestMapping(value = "/hos_MypageInfo/isValid.do")
	public ModelAndView isValid(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id, RedirectAttributes rAttr,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView mav = new ModelAndView();
	    HttpSession session=request.getSession();
		String hos_pwd=request.getParameter("hos_pwd");
		hosmypageinfoVO= new HosMypageInfoVO();
		hosmypageinfoVO.setHos_id(hos_id);
		hosmypageinfoVO.setHos_pwd(hos_pwd);
		Boolean result=hosmypageinfoService.isValid(hosmypageinfoVO);
		
		if(result) {
			session.setAttribute("isLogon", true);
	        String _hos_id= (String)session.getAttribute("log_id");
	        hosmypageinfoVO=hosmypageinfoService.selecthosInfo(_hos_id);
	        request.setAttribute("hosmypageinfoVO", hosmypageinfoVO);
	        mav.setViewName("redirect:/hos_MypageInfo/hosUserInformation.do?hos_id=" + hos_id);

		}else {
			PrintWriter out=response.getWriter();
			out.print("<script>alert('鍮꾨�踰덊샇瑜� �떎�떆 �솗�씤�빐二쇱꽭�슂.');window.history.back();</script>");
			rAttr.addAttribute("result", "failed");
			out.flush();
			mav.setViewName("redirect:/hos_MypageInfo/hosMypage.do");
		}
	    return mav;
	}


	@Override
	@RequestMapping(value="/hos_MypageInfo/modhosInfo.do")
	public ModelAndView modhosInfo(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("usertel_front") String usertelFront,
	        @RequestParam("usertel_end") String usertelEnd, @RequestParam("file") MultipartFile file, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("utf-8");
	    String hos_usertel = usertelFront + usertelEnd;
	    hosmypageinfoVO.setHos_usertel(hos_usertel);
	    String hos_code="hos0005";
	    
	    
	    String savePath = "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\imgRepo\\hos_images\\profile\\" + hos_code;

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
        
        hosmypageinfoVO.setHos_pro(file.getOriginalFilename());
	    
		hosmypageinfoService.modhosInfo(hosmypageinfoVO);
		HttpSession session=request.getSession();
		String _hos_id=(String)session.getAttribute("log_id");
		ModelAndView mav=new ModelAndView("redirect:/hos_MypageInfo/hosUserInformation.do?hos_id="+_hos_id);
		return mav;
	}

	@Override
	@RequestMapping(value="/hos_MypageInfo/modmyHosInfo.do")
	public ModelAndView modmyHosInfo(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		request.setCharacterEncoding("utf-8");

	    
		String [] hos365 = request.getParameterValues("hos_365");
    	String hos365Str = String.join(",", hos365);
		String hos_time_s=request.getParameter("hos_time_s");
		String hos_time_e=request.getParameter("hos_time_e");
    	String hos_dog=request.getParameter("hos_dog");
    	String hos_dog_value="0";
    	if(hos_dog!=null && hos_dog.equals("1")) {
    		hos_dog_value="1";
    	}
    	String hos_cat=request.getParameter("hos_cat");
    	String hos_cat_value="0";
    	if(hos_cat!=null && hos_cat.equals("1")) {
    		hos_cat_value="1";
    	}
    	String hos_small=request.getParameter("hos_small");
    	String hos_small_value="0";
    	if(hos_small!=null && hos_small.equals("1")) {
    		hos_small_value="1";
    	}
    	String hos_fish=request.getParameter("hos_fish");
    	String hos_fish_value="0";
    	if(hos_fish!=null && hos_fish.equals("1")) {
    		hos_fish_value="1";
    	}
    	String hos_bird=request.getParameter("hos_bird");
    	String hos_bird_value="0";
    	if(hos_bird!=null && hos_bird.equals("1")) {
    		hos_bird_value="1";
    	}
    	String hos_rep=request.getParameter("hos_rep");
    	String hos_rep_value="0";
    	if(hos_rep!=null && hos_rep.equals("1")) {
    		hos_rep_value="1";
    	}
    	String hos_etc=request.getParameter("hos_etc");
    	String hos_etc_value="0";
    	if(hos_etc!=null && hos_etc.equals("1")) {
    		hos_etc_value="1";
    	}
    	String hos_gs=request.getParameter("hos_gs");
    	String hos_gs_value="0";
    	if(hos_gs!=null && hos_gs.equals("1")) {
    		hos_gs_value="1";
    	}
    	String hos_im=request.getParameter("hos_im");
    	String hos_im_value="0";
    	if(hos_im!=null && hos_im.equals("1")) {
    		hos_im_value="1";
    	}
    	String hos_em=request.getParameter("hos_em");
    	String hos_em_value="0";
    	if(hos_em!=null && hos_em.equals("1")) {
    		hos_em_value="1";
    	}
    	String hos_rm=request.getParameter("hos_rm");
    	String hos_rm_value="0";
    	if(hos_rm!=null && hos_rm.equals("1")) {
    		hos_rm_value="1";
    	}
    	String hos_vac=request.getParameter("hos_vac");
    	String hos_vac_value="0";
    	if(hos_vac!=null && hos_vac.equals("1")) {
    		hos_vac_value="1";
    	}
    	String hos_24=request.getParameter("hos_24");
    	String hos_24_value="0";
    	if(hos_24!=null && hos_24.equals("1")) {
    		hos_24_value="1";
    	}
        hosmypageinfoVO.setHos_dog(hos_dog_value);
        hosmypageinfoVO.setHos_cat(hos_cat_value);
        hosmypageinfoVO.setHos_small(hos_small_value);
        hosmypageinfoVO.setHos_fish(hos_fish_value);
        hosmypageinfoVO.setHos_bird(hos_bird_value);
        hosmypageinfoVO.setHos_rep(hos_rep_value);
        hosmypageinfoVO.setHos_etc(hos_etc_value);
        hosmypageinfoVO.setHos_gs(hos_gs_value);
        hosmypageinfoVO.setHos_im(hos_im_value);
        hosmypageinfoVO.setHos_em(hos_em_value);
        hosmypageinfoVO.setHos_rm(hos_rm_value);
        hosmypageinfoVO.setHos_vac(hos_vac_value);
        hosmypageinfoVO.setHos_24(hos_24_value);
		hosmypageinfoVO.setHos_time_s(hos_time_s);
		hosmypageinfoVO.setHos_time_e(hos_time_e);
        System.out.println("�굹�삤�굹���끂�븘�끂�븘�뀠�궃���넄�삤�굹���끂"+hos_time_s); 
        String tel_front=request.getParameter("tel_front");
    	String tel_end=request.getParameter("tel_end");
    	String hos_tel=tel_front+tel_end;
    	hosmypageinfoVO.setHos_tel(hos_tel);
    	hosmypageinfoVO.setHos_365(hos365Str);
		
		hosmypageinfoService.modmyHosInfo(hosmypageinfoVO);
		HttpSession session=request.getSession();
		String hos_id=(String)session.getAttribute("log_id");
		ModelAndView mav=new ModelAndView("redirect:/hos_MypageInfo/hosInformation.do?hos_id="+hos_id);
		return mav;
	}
	
	//썸네일 등록 
	// multiFileList로 받기 -> 필수 MultipartHttpServletRequest req
	
	@RequestMapping(value = "/hos_MypageInfo/imgUpload.do", method = RequestMethod.POST)
	public void imgUpload(HosMypageInfoVO hosmypageinfoVO, MultipartHttpServletRequest req, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    //String hos_code = (String) session.getAttribute("hos_code");
	    String hos_code="hos0005";
	    
	    //수정 추가 ==========================================================================
	    
	    String hos_name=hosmypageinfoDAO.getHosName(hos_code);

	    List<MultipartFile> multipartFileList = new ArrayList<>(); // 1. 리스트 객체 생성 
	    try {
	        MultiValueMap<String, MultipartFile> files = req.getMultiFileMap(); //2. 멀티파일 받은 것을 files 객체 안에 삽입 
	        for (String key : files.keySet()) {
	            List<MultipartFile> fileList = files.get(key); //3. files의 값을 새로 만든 fileList안에 삽입 (key를 입력하면 value값을 가져옴)
	            for (MultipartFile file : fileList) { //4. fileList 끊길 때 까지 반복 
	                if (file.isEmpty()) continue; //5. file이 빌 때 까지 반복 (필수임 설명 확실 ㄴㄴ by 경민)
	                multipartFileList.add(file); //6. fileList 객체에서 하나씩 꺼낸 값을 file을 multipartFileList에 넣어줌 
	                // 여기까지는 사용자가 업로드한 이미지 파일이 총 몇개인지 세어주는 단계라고 볼수있다. 
	            }
	        }

	        if (multipartFileList.size() > 0) { 
	        	//7.session에서 받은 코드로 경로를 설정해준다. 
	            String savePath = "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\imgRepo\\hos_images\\thumbnail\\" + hos_code;

	            File directory = new File(savePath); //8. 7번에서 받아온 경로로 디렉터리 설정 , 7번 경로에 기존 hos#### 폴더가 존재하는지 확인하는 과정을 if문을 통해 진행 
	            if (directory.exists() && directory.isDirectory()) { //9. if문은 폴더 유무  
	                File[] existingFiles = directory.listFiles(); // 9번에서 찾은 폴더 안에 파일이 존재하던 안 하던 일단 existingFiles 배열에 삽입. 0~3 
	                for (File existingFile : existingFiles) { //10. for문  hos####폴더 안에 이미지 몇개가 있든 무조건 이미지 삭제함. 0~3
	                    existingFile.delete();
	                }
	            }

	            // 8. hos####이라는 폴더가 존재하지 않을 때, 디렉토리로 폴더 생성
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }
	            
	            // 9.폴더안에 이미지 삽입하기 

	            for (int i = 0; i < multipartFileList.size(); i++) { //10. 각 테이블 안에 삽입되는 사진이 다르기 때문에 for문을 돌린다. (다중이미지)
	                MultipartFile file = multipartFileList.get(i); 
	                if (file.isEmpty()) continue;
	                String originalFilename = file.getOriginalFilename(); //11. 6번에서 받은 파일 원본이름 받기 
	                String saveFilename = savePath + "\\" + originalFilename; // 12. 경로 + 11번에서 받은 파일의 원본 이름 합친 것을 savaFilename에 담기
	                file.transferTo(new File(saveFilename)); //13. transferTo 는 물리적으로 저장한다는 뜻 ex) file(i) 번째를 savaFilename으로 저장한다는 뜻. 

	                if (i == 0) {
	                    hosmypageinfoVO.setHimg1(originalFilename); // 14. 0 이면 himg1에 저장 ~~~~~hImg3까지 
	                } else if (i == 1) {
	                    hosmypageinfoVO.setHimg2(originalFilename);
	                } else if (i == 2) {
	                    hosmypageinfoVO.setHimg3(originalFilename);
	                }
	                System.out.println("파일 이름: " + originalFilename);
	            }
	            System.out.println("파일 개수 :" + multipartFileList.size());
	        }
	        
	        
	        String hos_id = (String) session.getAttribute("log_id");
	
	        String hosCode = hosmypageinfoDAO.searchuploadimg(hos_code); // 테이블에 신청 이력이 있는지 없는지 검색 
	        //15. 한번도 신청하지 않은 병원은 hoscode가 존재하지 않아서 쿼리를 두개로 나눔
	        if (hosCode == null || hosCode.isEmpty()) { //15-1. 한번도 신청하지 않았다면 insert 
	            hosmypageinfoVO.setHos_code(hos_code);
	            hosmypageinfoVO.setHos_id(hos_id); // 롱이 추가 
	            hosmypageinfoVO.setHos_name(hos_name);// 롱이 추가 
	            hosmypageinfoDAO.newuploadimg(hosmypageinfoVO);
	        } else {
	        	hosmypageinfoVO.setHos_code(hos_code); //15-2. 신청한 이력이 존재 update
	            hosmypageinfoVO.setHos_id(hos_id); // 롱이 추가
	            hosmypageinfoVO.setHos_name(hos_name);// 롱이 추가 
	        	hosmypageinfoService.uploadimg(hosCode, hosmypageinfoVO);

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	//프로필 이미지 불러오기 다운로드 보여주기 

	@RequestMapping(value = "/hos_MypageInfo/searchProfil.do")
    public ResponseEntity<byte[]> searchProfil(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
    	String hos_id = (String)session.getAttribute("log_id");
        
        try {
            String imagePath = hosmypageinfoService.searchProfil(hos_id);
            
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
            String blankImagePath = "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\img\\blankProfil.png";
            
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
}






	



