package com.sbs.example.easytextboard.Dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.Dto.Article;
import com.sbs.example.easytextboard.Dto.Board;

public class ArticleDao {
	private List<Article> articles;
	private int lastId;
	private int lastBoardId;
	private List<Board> boards;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastId = 0;
		
		boards = new ArrayList<>();
		lastBoardId = 0;

		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			write(1, "제목" + i, "내용" + i);
		}
		
		for (int i = 6; i <= 10; i++) {
			write(2, "제목" + i, "내용" + i);
		}
	}

	public int write(int memberId, String title, String body) {
		Article article = new Article();
		article.id = lastId + 1;
		article.memberId = memberId;
		article.title = title;
		article.body = body;
		articles.add(article);

		lastId = article.id;
		
		return article.id;
	}

	public List<Article> getForPrintArticles() {
		return articles;
	}

	public int makeBoard(String name) {
		Board board = new Board();
	    board.id = lastBoardId + 1;
		board.name = name;
		boards.add(board);

		lastBoardId = board.id;
		
		return board.id;
	}

}