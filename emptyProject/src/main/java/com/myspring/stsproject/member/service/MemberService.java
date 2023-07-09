package com.myspring.stsproject.member.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public String addMember(MemberVO memVO) throws DataAccessException;
	public void addHospital(MemberVO memVO) throws DataAccessException;
	public String selectUsercode(MemberVO memVO) throws DataAccessException;
	public String selectHoscode(MemberVO memVO) throws DataAccessException;
	public String searchId(MemberVO memberVO) throws DataAccessException;
	public boolean searchPass(MemberVO memberVO) throws DataAccessException;
	public String idChk(MemberVO memberVO) throws DataAccessException;
	public String HosidChk(MemberVO memberVO) throws DataAccessException;
	public void updateMember(MemberVO memVO) throws DataAccessException;
	public void removeMember(String id) throws DataAccessException;
	/* public MemberVO login(MemberVO memberVO) throws DataAccessException; */
	public boolean login(MemberVO memberVO) throws DataAccessException;
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException;
}
