package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 1번 게시물  저장소
		Article article1 = new Article();

		// 2번 게시물 저장소
		Article article2 = new Article();

		
		int lastArticleId = 0;
		
		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();
			
			if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				
				System.out.println("== 게시물 상세 ==");
				
				if (inputedId == 1) {
					if (article1.id == 0) {
						System.out.printf("%d번 게시물은 존재하지 않습니다. \n", inputedId);
						continue;
					}
					
					System.out.printf("번호 : %s\n", article1.id);
					System.out.printf("제목 : %s\n", article1.title);
					System.out.printf("내용 : %s\n", article1.body);
				}
			else if (inputedId == 2) {
					if (article2.id == 0) {
						System.out.printf("%d번 게시물은 존재하지 않습니다. \n", inputedId);
						continue;
					}
					
					System.out.printf("번호 : %s\n", article2.id);
					System.out.printf("제목 : %s\n", article2.title);
					System.out.printf("내용 : %s\n", article2.body);
				}
			else {
				System.out.printf("%d번 게시물은 존재하지 않습니다. \n", inputedId);
			}
			}
			else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				
				if (lastArticleId == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");
				
				if (lastArticleId >= 1) {
					System.out.printf("%d / %s\n", article1.id, article1.title);	
				}
				
				if (lastArticleId >= 2) {
					System.out.printf("%d / %s\n", article2.id, article2.title);
				}
			}
			else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				
				int id = lastArticleId + 1;
				String title;
				String body;
				
				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("내용 : ");
				body = scanner.nextLine();
				
				if ( id == 1 ) {
					article1.id = id;
					article1.title = title;
					article1.body = body;
				}
				
				else if ( id == 2 ) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}
				
				//System.out.printf("제목 : %s, 내용 : %s\n", title, body);
				
				System.out.printf("%d번 게시물이 생성되었습니다. \n", id);
				
				// 가장 마지막 게시물 번호를 갱신한다.
				// 방금 새 게시물이 생성되었기 때문.
				lastArticleId = id;
			}
			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
		}
		
		scanner.close();
	}
}
