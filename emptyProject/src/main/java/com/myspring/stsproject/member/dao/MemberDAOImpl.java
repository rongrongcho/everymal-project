package com.myspring.stsproject.member.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.stsproject.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MemberVO> selectAllMembers() throws DataAccessException {

		List<MemberVO> memberList=sqlSession.selectList("mapper.member.selectAllMembersList");
		return memberList;
	}


	@Override
	public void insertMember(MemberVO memVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertMember", memVO);
		
	}

	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memVO=(MemberVO) sqlSession.selectOne("mapper.member.findMember", id);
		return memVO;
	}

	@Override
	public void updateMember(MemberVO memVO) throws DataAccessException {
		sqlSession.update("mapper.member.updateMember", memVO);
		
	}

	@Override
	public void deleteMember(String id) throws DataAccessException {
		sqlSession.delete("mapper.member.deleteMember", id);
		
	}


	/*
	 * @Override public MemberVO loginCheck(MemberVO memberVO) throws
	 * DataAccessException { MemberVO
	 * member=sqlSession.selectOne("mapper.member.loginById", memberVO); return
	 * member; }
	 */
	
	@Override
	public boolean loginCheck(MemberVO memberVO) throws DataAccessException {
		boolean member=sqlSession.selectOne("mapper.member.loginById", memberVO);
		return member;
	}
	
	@Override
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException {
		HashMap<String,Object> member=sqlSession.selectOne("mapper.member.memberInfo", memberVO);
		return member;
	}

}
