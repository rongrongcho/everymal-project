package com.myspring.stsproject.member.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.stsproject.member.vo.MemberVO;

public interface MemberDAO {
	public List selectAllMembers() throws DataAccessException;
	public void insertMember(MemberVO memVO) throws DataAccessException;
	public MemberVO findMember(String id) throws DataAccessException;
	public void updateMember(MemberVO memVO) throws DataAccessException;
	public void deleteMember(String id) throws DataAccessException;
	/* public MemberVO loginCheck(MemberVO memberVO) throws DataAccessException; */
	public boolean loginCheck(MemberVO memberVO) throws DataAccessException;
	public HashMap<String,Object> memberInfo(MemberVO memberVO) throws DataAccessException;
}
