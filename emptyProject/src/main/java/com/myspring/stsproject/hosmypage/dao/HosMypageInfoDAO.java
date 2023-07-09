package com.myspring.stsproject.hosmypage.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;
import com.myspring.stsproject.member.vo.MemberVO;

public interface HosMypageInfoDAO {
	public boolean isValid(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
//	public HashMap<String,Object> selecthosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public HosMypageInfoVO selecthosInfo(String hos_id) throws DataAccessException;
	public void modhosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public void modmyHosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
//	public void modmyHosInfo2(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public String searchuploadimg(String hos_code) throws DataAccessException;
	public void uploadimg(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public void newuploadimg(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException;
	public String searchProfil(String hos_id) throws DataAccessException;
	public Map<String, String> searchThumb(String hos_code) throws DataAccessException;
	//Ãß°¡
	public String getHosName(String hos_code) throws DataAccessException;
	public HosMypageInfoVO rm_say(String hos_code) throws DataAccessException;
	public HosMypageInfoVO rm_status(String hos_code) throws DataAccessException;
}
