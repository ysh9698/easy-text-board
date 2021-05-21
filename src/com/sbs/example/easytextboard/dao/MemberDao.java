package com.sbs.example.easytextboard.Dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.Dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastId;

	public MemberDao() {
		members = new ArrayList<>();
		lastId = 0;

		makeTestData();
	}

	private void makeTestData() {
		join("aaa", "aaa", "aaa");
		join("ccc", "ccc", "ccc");
	}

	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();

		member.id = lastId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;

		members.add(member);

		lastId = member.id;

		return lastId;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberById(int id) {
		for (Member member : members) {
			if (member.id == id) {
				return member;
			}
		}
		return null;
	}
}
