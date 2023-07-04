package com.myspring.stsproject.forImg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("userProImgController")
public class userProImgControllerImpl implements userProImgController{

	private static String IMG_REPO="C:\\choronglee\\imgRepo\\user_profil";
	private static String profilefolder="\\profile"; 
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
	
	@Override
	@RequestMapping(value = "/proimgdown.do", method = RequestMethod.GET)
	public void proimgdown(@RequestParam String user_id, Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		model.addAttribute("user_id",user_id);
		String imageFileName=request.getParameter("imageFileName");
		System.out.println("userProImgController ->" + imageFileName);
		
		OutputStream outs=response.getOutputStream();
		String path=IMG_REPO+"\\"+user_id+"\\"+imageFileName;
		File imageFile=new File(path);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-diposition", imageFileName);
		FileInputStream fis=new FileInputStream(imageFile);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count=fis.read(buffer);
			if(count == -1) break;
			outs.write(buffer,0,count); 
		}
		fis.close();
		outs.close();
		
	}
	
	
}
