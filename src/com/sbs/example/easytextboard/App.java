package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	private Article[] articles = new Article[2];
	private int lastArticleId = 0;
	private int articlesSize = 0;
	
	private int articlesSize() {
		return articlesSize;
	}
	
	private Article getArticle(int id) {
		int index = getIndexById(id);
		
		if (index == -1) {
			return null;
		}
		
		return articles[index];
	}
	
	private boolean isArticlesFull() {
		return articlesSize == articles.length;
	}

	private int add(String title, String body) {
		// 만약에 현재 꽉 차 있다면
		// 새 업체와 계약한다.
		
		if ( isArticlesFull() ) {
			System.out.printf("== 배열 사이즈 증가(%d => %d) ==\n", articles.length, articles.length * 2);
			
			Article[] newArticles = new Article[articles.length * 2];
			
			for (int i = 0; i < articles.length; i++) {
				newArticles[i] = articles[i];
			}
			
			articles = newArticles;
		}
		
		Article article = new Article();
		
		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		
		articles[articlesSize] = article;
		
		articlesSize++;
		lastArticleId = article.id;
		
		return article.id;
	}

	private void remove(int Id) {
		int index = getIndexById(Id);
		
		if (index == -1) {
			return;
		}
		
		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}
		
		articlesSize--;
	}

	private int getIndexById(int id) {
		for ( int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}
		
		return -1;
	}
	
	private void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}
	
	// 가장 상위층 시작
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			
			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				
				String title;
				String body;
				
				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();
				
				int id = add(title, body);

				System.out.printf("%d번 게시물이 생성되었습니다. \n", id);
			}
			else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				
				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("번호 / 제목");
				
				for (int i = articlesSize() - 1; i >= 0; i-- ) {
					Article article = articles[i];

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			}
			else if (command.startsWith("article detail")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");
				
                Article article = getArticle(inputedId);
                
                if ( article == null ) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			}
			else if (command.startsWith("article modify")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 수정 ==");
				
                Article article = getArticle(inputedId);
                
                if ( article == null ) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : ");
			    String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				modify(inputedId, title, body);
				
				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);
				
			}
			else if (command.startsWith("article delete")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");
				
                Article article = getArticle(inputedId);
                
                if ( article == null ) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				
				remove(inputedId);
				
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);
			}
		}
		sc.close();
	}
}
