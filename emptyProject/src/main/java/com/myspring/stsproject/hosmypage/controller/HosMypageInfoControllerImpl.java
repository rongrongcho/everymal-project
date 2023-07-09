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
		String hos_code=(String)session.getAttribute("hos_code");
		HosMypageInfoVO hosInfo=hosmypageinfoService.selecthosInfo(_hos_id);
		Map<String, String> thumbMap = hosmypageinfoDAO.searchThumb(hos_code);
		System.out.println("thumbMap:::::::::"+thumbMap);
	    String himg1 = thumbMap.get("HIMG1");
	    String himg2 = thumbMap.get("HIMG2");
	    String himg3 = thumbMap.get("HIMG3");
	    hosInfo.setHimg1(himg1);
	    hosInfo.setHimg2(himg2);
	    hosInfo.setHimg3(himg3);
		mav.addObject("hosmypageinfoVO", hosInfo);
		HosMypageInfoVO hosrm=hosmypageinfoDAO.rm_say(hos_code);
		System.out.println("rmsay;;;;;"+hosrm);
		mav.addObject("hosmypagermsayVO",hosrm);
		HosMypageInfoVO hosstatus=hosmypageinfoDAO.rm_status(hos_code);
		mav.addObject("hosmypagermstatusVO",hosstatus);
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
		System.out.println("다시물어본다 낰낰22 " + mav);
		return mav;
	}
	
	@RequestMapping(value = "/hos_MypageInfo/hosInformation.do", method = RequestMethod.GET)
	public ModelAndView myHosInfo(@ModelAttribute("hosMypage") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id,  @RequestParam(value = "action", required = false) String action,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView(viewName);
		String _hos_id=(String) session.getAttribute("log_id");
		String hos_code=(String)session.getAttribute("hos_code");
		HosMypageInfoVO hosInfo=hosmypageinfoService.selecthosInfo(_hos_id);
		HosMypageInfoVO hosrm=hosmypageinfoDAO.rm_say(hos_code);
		HosMypageInfoVO hosstatus=hosmypageinfoDAO.rm_status(hos_code);
		System.out.println("hosCode;;;;;"+hos_code);
		System.out.println("rmstatys;;;;;;"+hosstatus);
		mav.addObject("hosmypagermsayVO",hosrm);
		mav.addObject("hosmypageinfoVO", hosInfo);
		mav.addObject("hosmypagermstatusVO",hosstatus);
		System.out.println("다시물어본다 낰낰33 " + mav);
		return mav;
	}
	
	
	@Override
	@RequestMapping(value = "/hos_MypageInfo/isValid.do")
	public ModelAndView isValid(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id, RedirectAttributes rAttr,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView mav = new ModelAndView();
	    HttpSession session=request.getSession();
	    response.setCharacterEncoding("UTF-8"); //alert한글 깨짐 현상 
		response.setContentType("text/html; charset=UTF-8");//alert한글 깨짐 현상 
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
	        //=========================이미지 ===================
	        mav.setViewName("redirect:/hos_MypageInfo/hosUserInformation.do?hos_id=" + hos_id);
	        

		}else {
			PrintWriter out=response.getWriter();
			out.print("<script>alert('비밀번호를 다시 확인해주세요.');window.history.back();</script>");
			out.flush();
			return null;
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
	    HttpSession session = request.getSession();
	    String hos_code = (String) session.getAttribute("hos_code");
	    //String hos_code="hos0001";
	    
	    
	    String savePath = "/Users/kyungminkim/Desktop/everymalSpring 2/emptyProject/src/main/webapp/resources/imgRepo/hos_images/profile/" + hos_code;

        // 기존 이미지 파일 삭제
        File directory = new File(savePath);
        if (directory.exists() && directory.isDirectory()) {
            File[] existingFiles = directory.listFiles();
            for (File existingFile : existingFiles) {
                existingFile.delete();
            }
        }

        // 디렉토리 생성
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // 이미지 파일 업로드 처리
        if (!file.isEmpty()) {
        	String originalFilename = file.getOriginalFilename();
            String saveFilename = savePath + "/" + originalFilename;
            file.transferTo(new File(saveFilename));
        }
        
        hosmypageinfoVO.setHos_pro(file.getOriginalFilename());
	    
		hosmypageinfoService.modhosInfo(hosmypageinfoVO);
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
        System.out.println("나오나와노아노아ㅗ난와놔오나와노"+hos_time_s); 
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
	
	
	@RequestMapping(value = "/hos_MypageInfo/imgUpload.do", method = RequestMethod.POST)
	public void imgUpload(HosMypageInfoVO hosmypageinfoVO, MultipartHttpServletRequest req, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    String hos_code = (String) session.getAttribute("hos_code");
	    //String hos_code="hos0001";
	    
	    List<MultipartFile> multipartFileList = new ArrayList<>();
	    try {
	        MultiValueMap<String, MultipartFile> files = req.getMultiFileMap();
	        for (String key : files.keySet()) {
	            List<MultipartFile> fileList = files.get(key);
	            for (MultipartFile file : fileList) {
	                if (file.isEmpty()) continue;
	                multipartFileList.add(file);
	            }
	        }

	        if (multipartFileList.size() > 0) {
	            String savePath = "/Users/kyungminkim/Desktop/everymalSpring 2/emptyProject/src/main/webapp/resources/imgRepo/hos_images/thumbnail/" + hos_code;

	            // 기존 이미지 파일 삭제
	            File directory = new File(savePath);
	            if (directory.exists() && directory.isDirectory()) {
	                File[] existingFiles = directory.listFiles();
	                for (File existingFile : existingFiles) {
	                    existingFile.delete();
	                }
	            }

	            // 디렉토리 생성
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            for (int i = 0; i < multipartFileList.size(); i++) {
	                MultipartFile file = multipartFileList.get(i);
	                if (file.isEmpty()) continue;
	                String originalFilename = file.getOriginalFilename();
	                String saveFilename = savePath + "/" + originalFilename;
	                file.transferTo(new File(saveFilename));

	                if (i == 0) {
	                    hosmypageinfoVO.setHimg1(originalFilename);
	                } else if (i == 1) {
	                    hosmypageinfoVO.setHimg2(originalFilename);
	                } else if (i == 2) {
	                    hosmypageinfoVO.setHimg3(originalFilename);
	                }
	                System.out.println("파일 이름: " + originalFilename);
	            }
	            System.out.println("파일 개수: " + multipartFileList.size());
	        }
	        
	        System.out.println("이미지 잘 들어가있니????"+hosmypageinfoVO.getHimg1());
            System.out.println("이미지 잘 들어가있니????"+hosmypageinfoVO.getHimg2());
            System.out.println("이미지 잘 들어가있니????"+hosmypageinfoVO.getHimg3());
	        
	        String hos_id = (String) session.getAttribute("log_id");
	        String hosCode = hosmypageinfoDAO.searchuploadimg(hos_code);
	        System.out.println("넘나 빡쳐염;;;;;;;;;;"+hosCode);
	        if (hosCode == null || hosCode.isEmpty()) {
	            hosmypageinfoVO.setHos_code(hos_code);
	            hosmypageinfoDAO.newuploadimg(hosmypageinfoVO);
	            System.out.println("코드 잘있니????" + hosmypageinfoVO.getHos_code());
	            System.out.println("코드 잘있니????"+hosmypageinfoVO.getHos_code());
	            System.out.println("이미지 잘 들어가있니????2트.."+hosmypageinfoVO.getHimg1());
	            System.out.println("이미지 잘 들어가있니????2트.."+hosmypageinfoVO.getHimg2());
	            System.out.println("이미지 잘 들어가있니????2트.."+hosmypageinfoVO.getHimg3());
	        } else {
	        	hosmypageinfoVO.setHos_code(hos_code);
	            hosmypageinfoService.uploadimg(hosCode, hosmypageinfoVO);
	            System.out.println("이미지 잘 들어가있니????3트.."+hosmypageinfoVO.getHimg1());
	            System.out.println("이미지 잘 들어가있니????3트.."+hosmypageinfoVO.getHimg2());
	            System.out.println("이미지 잘 들어가있니????3트.."+hosmypageinfoVO.getHimg3());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value = "/hos_MypageInfo/searchProfil.do")
    public ResponseEntity<byte[]> searchProfil(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
    	String hos_id = (String)session.getAttribute("log_id");
    	String hos_code=(String)session.getAttribute("hos_code");
        
        try {
            String imagePath = hosmypageinfoService.searchProfil(hos_id, hos_code);
            
            // 이미지 파일을 읽어들입니다.
            File imageFile = new File(imagePath);
            FileInputStream fis = new FileInputStream(imageFile);
            
            // 이미지를 byte 배열로 변환합니다.
            byte[] imageBytes = new byte[(int) imageFile.length()];
            fis.read(imageBytes);
            
            // HTTP 응답으로 이미지를 전송합니다.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            
            // 예외 발생 시 빈 프로필 이미지를 전송합니다.
            String blankImagePath = "/Users/kyungminkim/Desktop/everymalSpring 2/emptyProject/src/main/webapp/resources/img/blankProfil.png";
            
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
                
                // 빈 프로필 이미지를 생성하지 못하는 경우 500 Internal Server Error 응답을 반환합니다.
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}






	



