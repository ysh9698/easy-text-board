package com.sbs.example.easytextboard.session;

public class Session {

	public int loginedMemberId;
	public int selectedBoardId;
	
	public Session() {
		loginedMemberId = 0;
	}

	public void login(int id) {
		loginedMemberId = id;
	}
	
	public void logout() {
		loginedMemberId = 0;
	}

	public boolean isLogined() {
		return loginedMemberId > 0;
	}

}
