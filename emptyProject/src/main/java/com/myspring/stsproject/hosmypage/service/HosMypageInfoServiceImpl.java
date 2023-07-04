package com.myspring.stsproject.hosmypage.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myspring.stsproject.hosmypage.dao.HosMypageInfoDAO;
import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

	@Service("hosmypageinfoService")
	public class HosMypageInfoServiceImpl implements HosMypageInfoService {
		
		@Autowired
		private HosMypageInfoDAO hosmypageinfoDAO;
		
	
		@Override
		public boolean isValid(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
			return hosmypageinfoDAO.isValid(hosmypageinfoVO);
		}
		
		@Override
		public HosMypageInfoVO selecthosInfo(String hos_id) throws DataAccessException{
			HosMypageInfoVO hosmypageinfoVO=(HosMypageInfoVO)hosmypageinfoDAO.selecthosInfo(hos_id);
			return hosmypageinfoVO;
		}
		
		@Override
		public void modhosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
			hosmypageinfoDAO.modhosInfo(hosmypageinfoVO);
		}
		
		@Override
		public void modmyHosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
			hosmypageinfoDAO.modmyHosInfo(hosmypageinfoVO);
			//hosmypageinfoDAO.modmyHosInfo2(hosmypageinfoVO);
		}

		@Override
		public void uploadimg(String hos_code, HosMypageInfoVO hosmypageinfoVO) throws DataAccessException {
		    String hosCode = hosmypageinfoDAO.searchuploadimg(hos_code);
		    if (hosCode == null || hosCode.isEmpty()) {
		        hosmypageinfoVO.setHos_code(hos_code);
		        hosmypageinfoDAO.newuploadimg(hosmypageinfoVO);
		    } else {
		        hosmypageinfoDAO.uploadimg(hosmypageinfoVO);
		    }
		}
		
		@Override
		public String searchProfil(String hos_id) throws DataAccessException {
		    String hosPro = hosmypageinfoDAO.searchProfil(hos_id);
		    if (hosPro == null || hosPro.isEmpty()) {
		        return "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\imgRepo\\hos_images\\profile\\img\\blankProfil.png";
		    } else {
		        String hos_code = "hos0001";
		        return "C:\\choronglee\\stsProject\\emptyProject\\src\\main\\webapp\\resources\\imgRepo\\hos_images\\profile\\" + hos_code + "\\" + hosPro;
		    }
		}


//		@Override
//		public void modmyHosInfo2(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
//			hosmypageinfoDAO.modmyHosInfo2(hosmypageinfoVO);
//		}
	}


