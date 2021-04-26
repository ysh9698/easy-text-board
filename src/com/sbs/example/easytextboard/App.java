package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Article[] articles = new Article[2];
	int lastArticleId = 0;
	
	Article getArticle(int id) {
		if (id < 1 ) {
			return null;
		}
		
		if (id > lastArticleId) {
			return null;
		}
		
		return articles[id - 1];
	}

	public void run() {
		articles[0] = new Article();
		articles[1] = new Article();
		
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
		
		
		Scanner sc = new Scanner(System.in);
		
		int maxArticlesCount = articles.length;
		
		while (true) {
			
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			
			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				
				if ( lastArticleId >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}
				
				int id = lastArticleId + 1;
				String title;
				String body;
				
				lastArticleId = id;
				
				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();
				
				Article article = getArticle(id);
				
				article.id = id;
				article.title = title;
				article.body = body;
				
				System.out.printf("%d번 게시물이 생성되었습니다. \n", id);
			}
			else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				
				if (lastArticleId == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("번호 / 제목");
				
				for (int i = 1; i <= lastArticleId; i++ ) {
					Article article = getArticle(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			}
			else if (command.startsWith("article detail")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");
				
				if ( lastArticleId == 0 || inputedId > lastArticleId ) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
                Article article = getArticle(inputedId);
				
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			}
		}
		sc.close();
	}

}
