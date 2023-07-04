package com.myspring.stsproject.member.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.stsproject.member.dao.MemberDAO;
import com.myspring.stsproject.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;


	@Override
	public List listMembers() throws DataAccessException {
		List memberList = memberDAO.selectAllMembers();
		return memberList;
	}



	@Override
	public void addMember(MemberVO memVO) throws DataAccessException {
		memberDAO.insertMember(memVO);
	}



	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memVO=(MemberVO)memberDAO.findMember(id);
		return memVO;
	}



	@Override
	public void updateMember(MemberVO memVO) throws DataAccessException {
		memberDAO.updateMember(memVO);
		
	}



	@Override
	public void removeMember(String id) throws DataAccessException {
		memberDAO.deleteMember(id);
		
	}



	/*
	 * @Override public MemberVO login(MemberVO memberVO) throws DataAccessException
	 * { return memberDAO.loginCheck(memberVO); }
	 */
	
	@Override
	public boolean login(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginCheck(memberVO);
	}
	
	@Override
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException {
		return memberDAO.memberInfo(memberVO);
	}

}
