package com.sbs.example.easytextboard.controller;

import java.util.Scanner;

import com.sbs.example.easytextboard.Dto.Member;
import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		} else if (cmd.equals("member login")) {
			login(cmd);
		} else if (cmd.equals("member logout")) {
			logout(cmd);
		} else if (cmd.equals("member whoami")) {
			whoami(cmd);
		}
	}

	private void logout(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		Container.session.logout();
		System.out.println("로그아웃 되었습니다.");

	}

	private void whoami(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int loginedMemberId = Container.session.loginedMemberId;

		Member loginedMember = memberService.getMemberById(loginedMemberId);

		System.out.printf("== 로그인된 사용자정보 ==\n");
		System.out.printf("로그인아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
	}

	private void login(String cmd) {
		Scanner sc = Container.scanner;

		String loginId;
		String loginPw;

		System.out.printf("로그인아이디 : ");
		loginId = sc.nextLine();

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			System.out.printf("%s(은)는 존재하지 않는 로그인 아이디 입니다.\n", loginId);
			return;
		}

		System.out.printf("로그인비번 : ");
		loginPw = sc.nextLine();

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		Container.session.login(member.id);
		System.out.printf("%s님, 환영합니다.\n", member.name);
	}

	private void join(String cmd) {
		Scanner sc = Container.scanner;

		String loginId;
		String loginPw;
		String name;

		System.out.printf("로그인아이디 : ");
		loginId = sc.nextLine();

		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);

		if (isJoinableLoginId == false) {
			System.out.printf("%s(은)는 이미 사용중인 로그인아이디 입니다.\n", loginId);
			return;
		}

		System.out.printf("로그인비번 : ");
		loginPw = sc.nextLine();

		System.out.printf("이름 : ");
		name = sc.nextLine();

		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
	}

}