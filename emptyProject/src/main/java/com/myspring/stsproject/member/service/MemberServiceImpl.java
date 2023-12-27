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
	public String addMember(MemberVO memVO) throws DataAccessException {
		String userCode = (String)memberDAO.insertMember(memVO);
		return userCode;
	}
	
	@Override
	public void addHospital(MemberVO memVO) throws DataAccessException {
		memberDAO.addHospital(memVO);
	}
	
	@Override
	public String selectUsercode(MemberVO memVO) throws DataAccessException {
		String code = (String)memberDAO.selectUsercode(memVO);
		return code;
	}
	
	@Override
	public String selectHoscode(MemberVO memVO) throws DataAccessException {
		String code = (String)memberDAO.selectHoscode(memVO);
		return code;
	}

	@Override
	public String searchId(MemberVO memVO) throws DataAccessException {
		String id=(String)memberDAO.searchId(memVO);
		return id;
	}
	
	@Override
	public boolean searchPass(MemberVO memberVO) throws DataAccessException {
		boolean result = (boolean)memberDAO.searchPass(memberVO);
		return result;
	}
	
	@Override
	public String idChk(MemberVO memVO) throws DataAccessException {
		String id=(String)memberDAO.idChk(memVO);
		return id;
	}
	
	@Override
	public String HosidChk(MemberVO memVO) throws DataAccessException {
		String id=(String)memberDAO.HosidChk(memVO);
		return id;
	}

	@Override
	public void updateMember(MemberVO memVO) throws DataAccessException {
		memberDAO.updateMember(memVO);
		
	}



	@Override
	public void removeMember(String id) throws DataAccessException {
		memberDAO.deleteMember(id);
		
	}


	
	@Override
	public boolean login(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginCheck(memberVO);
	}
	
	@Override
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException {
		return memberDAO.memberInfo(memberVO);
	}

}
