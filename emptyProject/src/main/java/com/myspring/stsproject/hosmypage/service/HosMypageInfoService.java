package com.myspring.stsproject.hosmypage.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;
import com.myspring.stsproject.member.vo.MemberVO;

public interface HosMypageInfoService {
	public boolean isValid(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public HosMypageInfoVO selecthosInfo(String hos_id) throws DataAccessException;
	public void modhosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public void modmyHosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
//	public String uploadimg(String hos_code) throws DataAccessException;
//	public void modmyHosInfo2(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public void uploadimg(String hos_code, HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	//public String searchProfil(String hos_id) throws DataAccessException;
	//public String searchProfil(String hos_id, HttpServletRequest request) throws DataAccessException;
	public String searchProfil(String hos_id, String hos_code) throws DataAccessException;
	

	//	public HosMypageInfoVO callhosInfo(String hos_id) throws DataAccessException;
}
