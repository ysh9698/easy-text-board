package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	private Article[] articles;
	private int lastArticleId;
	private int articlesSize;

	public App() {
		articles = new Article[32];
		lastArticleId = 0;
		articlesSize = 0;

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

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
		// 새 업체과 계약한다.

		if (isArticlesFull()) {
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

	private void remove(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return;
		}

		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}

		articlesSize--;
	}

	private int getIndexById(int id) {
		for (int i = 0; i < articlesSize(); i++) {
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
			} else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				String title;
				String body;

				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();

				int id = add(title, body);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
			} else if (command.startsWith("article search ")) {

				String[] commandBits = command.split(" ");

				String searchKeyword = commandBits[2];

				int page = 1;

				if (commandBits.length >= 4) {
					page = Integer.parseInt(commandBits[3]);
				}

				if (page <= 1) {
					page = 1;
				}

				System.out.println("== 게시물 검색 ==");

				int searchResultArticlesLen = 0;

				// 검색된 결과의 수를 먼저 구하기
				for (Article article : articles) {
					if (article == null) {
						break;
					}

					if (article.title.contains(searchKeyword)) {
						searchResultArticlesLen++;
					}
				}

				Article[] searchResultArticles = new Article[searchResultArticlesLen];

				int searchResultArticlesIndex = 0;
				for (Article article : articles) {
					if (article == null) {
						break;
					}

					if (article.title.contains(searchKeyword)) {
						searchResultArticles[searchResultArticlesIndex] = article;
						searchResultArticlesIndex++;
					}
				}

				if (searchResultArticles.length == 0) {
					System.out.println("검색결과가 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				int itemsInAPage = 10;
				int startPos = searchResultArticles.length - 1;
				startPos -= (page - 1) * itemsInAPage;
				int endPos = startPos - (itemsInAPage - 1);

				if (endPos < 0) {
					endPos = 0;
				}

				if (startPos < 0) {
					System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				for (int i = startPos; i >= endPos; i--) {
					Article article = searchResultArticles[i];

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			} else if (command.startsWith("article list ")) {
				int page = Integer.parseInt(command.split(" ")[2]);

				if (page <= 1) {
					page = 1;
				}

				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				int itemsInAPage = 10;
				int startPos = articlesSize() - 1;
				startPos -= (page - 1) * itemsInAPage;
				int endPos = startPos - (itemsInAPage - 1);

				if (endPos < 0) {
					endPos = 0;
				}

				if (startPos < 0) {
					System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				for (int i = startPos; i >= endPos; i--) {
					Article article = articles[i];

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			} else if (command.startsWith("article detail ")) {
				int inputedId = 0;
				
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				}
				catch ( NumberFormatException e ) {
					System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
					continue;
				}
				
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			} else if (command.startsWith("article modify ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 수정 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
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

			} else if (command.startsWith("article delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
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