package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.Controller;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;

public class App {
	MemberController memberController;
	ArticleController articleController;

	public App() {
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();
		
		init();
	}

	private void init() {
		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardId = articleService.getDefaultBoardId();
	}

	private void makeTestData() {
		// 회원 2명 생성
		MemberService memberService = Container.memberService;
		int firstMemberId = memberService.join("aaa", "aaa", "aaa");
		int secondMemberId = memberService.join("bbb", "bbb", "bbb");

		// 공지사항 게시판 생성
		ArticleService articleService = Container.articleService;
		int noticeBoardId = articleService.makeBoard("공지사항");
		int freeBoardId = articleService.makeBoard("자유");

		// 1번 회원이 공지사항 게시물 5개 작성
		for (int i = 1; i <= 5; i++) {
			articleService.write(noticeBoardId, firstMemberId, "제목" + i, "내용" + i);
		}

		// 2번 회원이 공지사항 게시물 5개 작성
		for (int i = 1; i <= 5; i++) {
			articleService.write(noticeBoardId, secondMemberId, "제목" + i, "내용" + i);
		}
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("system exit")) {
				break;
			}

			Controller controller = getControllerByCmd(cmd);
			if (controller != null) {
				controller.doCommand(cmd);
			}
		}

		sc.close();
	}

	private Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;
		}

		if (cmd.startsWith("article ")) {
			return articleController;
		}

		return null;
	}
}