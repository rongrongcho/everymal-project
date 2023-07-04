package com.myspring.stsproject.member.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public void addMember(MemberVO memVO) throws DataAccessException;
	public MemberVO findMember(String id) throws DataAccessException;
	public void updateMember(MemberVO memVO) throws DataAccessException;
	public void removeMember(String id) throws DataAccessException;
	/* public MemberVO login(MemberVO memberVO) throws DataAccessException; */
	public boolean login(MemberVO memberVO) throws DataAccessException;
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException;
}
