package com.sbs.example.easytextboard.service;

import com.sbs.example.easytextboard.Dao.MemberDao;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

}
