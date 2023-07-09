package com.myspring.stsproject.hosmypage.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosmypage.vo.HosMypageInfoVO;

@Repository("hosMypageInfoDAO")
public class HosMypageInfoDAOImpl implements HosMypageInfoDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean isValid(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
		Boolean result=sqlSession.selectOne("mapper.hosMypage.isValid", hosmypageinfoVO);
		return result != null ? result.booleanValue() : false;
	}

	@Override
	public HosMypageInfoVO selecthosInfo(String hos_id) throws DataAccessException{
		HosMypageInfoVO hosmypageinfoVO=(HosMypageInfoVO)sqlSession.selectOne("mapper.hosMypage.selecthosInfo", hos_id);
		return hosmypageinfoVO;
	}

	@Override
	public void modhosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
		sqlSession.update("mapper.hosMypage.modhosInfo", hosmypageinfoVO);
	}

	@Override
	public void modmyHosInfo(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException{
		sqlSession.update("mapper.hosMypage.modmyHosInfo", hosmypageinfoVO);
		sqlSession.update("mapper.hosMypage.modmyHosInfo2", hosmypageinfoVO);

	}

	@Override
	public void uploadimg(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException {
	    sqlSession.update("mapper.hosMypage.uploadimg", hosmypageinfoVO);
	    sqlSession.update("mapper.hosMypage.updateThum",hosmypageinfoVO); // hosjoin thum 컬럼 업데이트 
	    sqlSession.update("mapper.hosMypage.updateStatus",hosmypageinfoVO); //검토중 업데이트
	    sqlSession.insert("mapper.hosMypage.insertRMtbl", hosmypageinfoVO); //rm tbl 데이터 삽입
	}

	@Override
	public void newuploadimg(HosMypageInfoVO hosmypageinfoVO) throws DataAccessException {
	    sqlSession.insert("mapper.hosMypage.newuploadimg", hosmypageinfoVO);
	    sqlSession.update("mapper.hosMypage.updateThum",hosmypageinfoVO); // hosjoin thum 컬럼 업데이트
	    sqlSession.update("mapper.hosMypage.updateStatus",hosmypageinfoVO);//검토중 업데이트
	    sqlSession.insert("mapper.hosMypage.insertRMtbl", hosmypageinfoVO); //rm tbl 데이터 삽입
	}

	@Override
	public String searchuploadimg(String hos_code) throws DataAccessException {
	    return sqlSession.selectOne("mapper.hosMypage.searchuploadimg", hos_code);
	}

	@Override
	public String searchProfil(String hos_id) throws DataAccessException {
	    return sqlSession.selectOne("mapper.hosMypage.searchProfil", hos_id);
	}
	
	@Override
	public Map<String, String> searchThumb(String hos_code) throws DataAccessException {
	    return sqlSession.selectOne("mapper.hosMypage.searchThumb", hos_code);
	}

	@Override
	public String getHosName(String hos_code) throws DataAccessException {
		return sqlSession.selectOne("mapper.hosMypage.getHosName",hos_code);
	}

	@Override
	public HosMypageInfoVO rm_say(String hos_code) throws DataAccessException {
	    return sqlSession.selectOne("mapper.hosMypage.rmSay", hos_code);
	}
	
	@Override
	public HosMypageInfoVO rm_status(String hos_code) throws DataAccessException {
	    return sqlSession.selectOne("mapper.hosMypage.rmStatus", hos_code);
	}
}

