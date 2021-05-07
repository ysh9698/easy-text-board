package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int lastMemberId;

	public MemberController() {
		lastMemberId = 0;
		members = new ArrayList<>();
	}

	private int join(String loginId, String loginPw, String name) {
		Member member = new Member();

		member.id = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		members.add(member);
		lastMemberId = member.id;

		return member.id;
	}

	private boolean isJoinAvailabelLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}

		return true;
	}

	public void run(Scanner sc, String command) {
		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			String loginId = "";
			String loginPw;
			String name;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;

			while (true) {
				if (loginIdMaxCount <= loginIdCount) {
					System.out.println("회원가입을 취소합니다.");
					break;
				}

				System.out.printf("로그인아이디 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				} else if (isJoinAvailabelLoginId(loginId) == false) {
					loginIdCount++;
					System.out.printf("%s(은)는 이미 사용중이 로그인아이디 입니다.\n", loginId);
					continue;
				}

				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}

			while (true) {
				System.out.printf("로그인비번 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					continue;
				}

				break;
			}

			while (true) {
				System.out.printf("이름 : ");
				name = sc.nextLine().trim();

				if (name.length() == 0) {
					continue;
				}

				break;
			}

			int id = join(loginId, loginPw, name);

			System.out.printf("%d번 회원이 생성되었습니다.\n", id);
		}
	}
}