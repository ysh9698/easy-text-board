package com.sbs.example.easytextboard.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.Dto.Article;
import com.sbs.example.easytextboard.Dto.Member;
import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article add")) {
			add(cmd);
		}
		else if (cmd.equals("article list")) {
			list(cmd);
		}
		else if (cmd.equals("article makeBoard")) {
			makeBoard(cmd);
		}
	}
	
	
	private void makeBoard(String cmd) {
		Scanner sc = Container.scanner;
		
		String name;

		System.out.printf("게시판 이름 : ");
		name = sc.nextLine();
		
		int BoardId = articleService.makeBoard(name);
		
		System.out.printf("공지사항(%d번) 게시판이 생성되었습니다.", BoardId);
	}

	private void list(String cmd) {
		List<Article> articles = articleService.getForPrintArticles();
		
		System.out.println("번호 / 작성자 / 제목");
		
		for (Article article : articles) {
			Member member = memberService.getMemberById(article.memberId);
			System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
		}
	}

	private void add(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		Scanner sc = Container.scanner;

		String title;
		String body;

		System.out.printf("제목 : ");
		title = sc.nextLine();

		System.out.printf("내용 : ");
		body = sc.nextLine();
		
		int boardId = Container.session.selectedBoardId;
		int memberId = Container.session.loginedMemberId;

		int id = articleService.write(boardId, memberId, title, body);
		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	  }
	}