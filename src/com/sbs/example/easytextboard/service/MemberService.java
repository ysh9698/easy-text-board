package com.sbs.example.easytextboard.service;

import com.sbs.example.easytextboard.Dao.MemberDao;
import com.sbs.example.easytextboard.Dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public boolean isJoinableLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);
		
		if (member != null) {
		return false;
	}
		
		return true;

 }
}