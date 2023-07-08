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
	public String insertMember(MemberVO memVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertMember", memVO);
		String userCode = memVO.getUser_code();
		return userCode;
	}
	
	@Override
	public void addHospital(MemberVO memVO) throws DataAccessException {
		sqlSession.insert("mapper.member.addHospital", memVO);
		
	}
	
	@Override
	public String selectUsercode(MemberVO memVO) throws DataAccessException {
		String code =sqlSession.selectOne("mapper.member.selectUsercode", memVO);
		return code;
	}
	
	@Override
	public String selectHoscode(MemberVO memVO) throws DataAccessException {
		String code =sqlSession.selectOne("mapper.member.selectHoscode", memVO);
		return code;
	}

	@Override
	public String searchId(MemberVO memVO) throws DataAccessException {
		String id=(String)sqlSession.selectOne("mapper.member.searchId", memVO);
		return id;
	}
	
	@Override
	public boolean searchPass(MemberVO memberVO) throws DataAccessException {
		boolean result=sqlSession.selectOne("mapper.member.searchPass", memberVO);
		return result;
	}
	
	@Override
	public String idChk(MemberVO memVO) throws DataAccessException {
		String id=(String)sqlSession.selectOne("mapper.member.idChk", memVO);
		return id;
	}
	
	@Override
	public String HosidChk(MemberVO memVO) throws DataAccessException {
		String id=(String)sqlSession.selectOne("mapper.member.HosidChk", memVO);
		return id;
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
