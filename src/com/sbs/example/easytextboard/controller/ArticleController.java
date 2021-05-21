package com.sbs.example.easytextboard.controller;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = new ArticleService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article add")) {
			add(cmd);
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

		int id = articleService.write(Container.session.loginedMemberId, title, body);
		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	  }
	}