package com.sbs.example.easytextboard.Dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbs.example.easytextboard.Dto.Article;
import com.sbs.example.easytextboard.Dto.Board;
import com.sbs.example.easytextboard.container.Container;

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

	}

	public int write(int boardId, int memberId, String title, String body) {
		Article article = new Article();
		article.id = lastId + 1;
		article.boardId = boardId;
		article.memberId = memberId;
		article.title = title;
		article.body = body;
		articles.add(article);

		lastId = article.id;

		return article.id;
	}

	public List<Article> getForPrintArticles(int boardId) {
		List<Article> newArticles = new ArrayList<>();

		for (Article article : articles) {
			if (article.boardId == boardId) {
				newArticles.add(article);
			}
		}

		Collections.reverse(newArticles);

		return newArticles;
	}

	public int makeBoard(String name) {
		Board board = new Board();
		board.id = lastBoardId + 1;
		board.name = name;
		boards.add(board);

		lastBoardId = board.id;

		return board.id;
	}

	public Board getBoardById(int boardId) {
		for (Board board : boards) {
			if (board.id == boardId) {
				return board;
			}
		}

		return null;
	}

	public List<Board> getBoards() {
		return boards;
	}

}