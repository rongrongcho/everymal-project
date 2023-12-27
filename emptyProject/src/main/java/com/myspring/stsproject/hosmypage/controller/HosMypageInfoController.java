package com.myspring.stsproject.hosmypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

public interface HosMypageInfoController {
	public ModelAndView isValid(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("hos_id") String hos_id, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modmyHosInfo(@ModelAttribute("hosMypageInfoVO") HosMypageInfoVO hosmypageinfoVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modhosInfo(HosMypageInfoVO hosmypageinfoVO, String usertelFront, String usertelEnd, MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
