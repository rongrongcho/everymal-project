package com.myspring.stsproject.forImg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller("hosProController")
public class HosProControllerImpl implements HosProController {

	private static String IMG_REPO="C:\\choronglee\\imgRepo\\hos_images";
	private static String profilefolder="\\profile"; //병원 프로필 
	private static final long serialVersionUID = 1L;
	
	
	@Override
	@RequestMapping(value = "/hosProImgDown.do", method = RequestMethod.GET)	                          
	public void hosProImgDown(@RequestParam String hos_pro, @RequestParam String hos_code, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		model.addAttribute("hos_pro",hos_pro);		//URL�� �ִ� hos_pro�� ��
		model.addAttribute("hos_code",hos_code);
		//String hos_code=request.getParameter("hos_code");
		System.out.println("�߹��� ġ�Ϳ� �̹��� �ҷ����� ���� ȣ���ڵ�:" + hos_code);
		//String hos_pro=request.getParameter("hos_pro");
		System.out.println("�߹��� ġ�Ϳ� �̹��� �ҷ����� ���� ���ϸ�:" + hos_pro);
		OutputStream outs=response.getOutputStream();
		String path=IMG_REPO+profilefolder+"\\"+hos_code+"\\"+hos_pro;
		File imageFile=new File(path);
		//�̹��� ���� �ٿ�ε�� �ʿ��� response ��� ������ ���� 
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-diposition", hos_pro);
		FileInputStream fis=new FileInputStream(imageFile);
		byte[] buffer = new byte[1024*8]; //���۸� �̿��� �ѹ��� 8K �� ����
		while(true) {
			int count=fis.read(buffer);
			if(count == -1) break;
			outs.write(buffer,0,count); // buffer�� �ִ°� 0���� count ��ŭ
		}
		fis.close();
		outs.close();
		
		
	}

}
