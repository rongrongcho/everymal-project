package com.myspring.stsproject.hosResInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosMyReplyInfo.vo.ReplyVO;
import com.myspring.stsproject.hosResInfo.vo.ResVO;

@Repository("hosresDAO")
public class HosResDAOImpl implements HosResDAO{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List selectAllReservation(Map<String, Integer> pagingMap, String hos_code) throws DataAccessException {
		List<ResVO> resList = new ArrayList<ResVO>();
        int section = pagingMap.get("section");
        int pageNum = pagingMap.get("pageNum");
        Map map = new HashMap();
        map.put("section", section);
        map.put("pageNum", pageNum);
        map.put("hos_code",hos_code);
        //
        resList = sqlSession.selectList("mapper.hosResInfo.selectAllReservation", map);
		 
		return resList;
	}

	@Override
	public int rescount(String hos_code) throws DataAccessException {
		int res_count= sqlSession.selectOne("mapper.hosResInfo.rescount", hos_code);
		return res_count;
	}

	@Override
	public ResVO selectRes(String res_code) throws DataAccessException {
		ResVO reservation=sqlSession.selectOne("mapper.hosResInfo.selectRes", res_code);
		return reservation;
	}

}
