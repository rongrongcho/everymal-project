package com.myspring.stsproject.userHistory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

public interface UserHistoryController {
	public ModelAndView callMyHosReserv(@ModelAttribute("UserHistoryVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("log_id") String hos_id, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewMyHosReserv(@ModelAttribute("UserHistoryVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("log_id") String hos_id, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView callMyTaxiReserv(@ModelAttribute("UserHistoryVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("log_id") String hos_id, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewMyTaxiReserv(@ModelAttribute("UserHistoryVO") HosMypageInfoVO hosmypageinfoVO, @RequestParam("log_id") String hos_id, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
