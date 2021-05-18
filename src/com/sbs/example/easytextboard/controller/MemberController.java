package com.sbs.example.easytextboard.controller;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.service.MemberService;

public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		}
		else if (cmd.equals("member login")) {
			
		}
	}

	private void join(String cmd) {
		Scanner sc = Container.scanner;
		
		String loginId;
		String loginPw;
		String name;
		
		System.out.printf("로그인아이디 : ");
		loginId = sc.nextLine();
		
		System.out.printf("로그인비번 : ");
		loginPw = sc.nextLine();
		
		System.out.printf("이름 : ");
		name = sc.nextLine();
		
		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
	}

}