package com.myspring.stsproject.hosMyReplyInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.hosMyReplyInfo.vo.ReplyVO;
import com.myspring.stsproject.hosReviewInfo.vo.ReviewVO;


@Repository("hosMyReplyDAO")
public class HosMyReplyDAOImpl implements HosMyReplyDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllReplyList(Map<String, Integer> pagingMap, String hos_id) throws DataAccessException {
		 List<ReplyVO> replyList = new ArrayList<ReplyVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");
         Map map = new HashMap();
         map.put("section", section);
         map.put("pageNum", pageNum);
         map.put("hos_id", hos_id);
         //
         replyList = sqlSession.selectList("mapper.hosMyReply.selectAllReplyList", map);
		 
		return replyList;
	}

	@Override
	public int replyCount(String hos_id) throws DataAccessException {
		int reply_count=0;
		reply_count=sqlSession.selectOne("mapper.hosMyReply.replyCount", hos_id);
		return reply_count;
	}

	@Override
	public void deleteReply(String[] items) throws DataAccessException {
		String[]a_code=items;
		if(a_code==null || a_code.length==0) {
			 System.out.println("오류,선택된 게시글의 코드가 존재하지않습니다.");
		 }else {			 		 		 			
			 Map map=new HashMap();
			 map.put("array", a_code);
			 sqlSession.update("mapper.hosMyReply.deleteReply", map);					
		 }
		
	}

}
