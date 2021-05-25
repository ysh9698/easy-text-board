package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.Dao.ArticleDao;
import com.sbs.example.easytextboard.Dto.Article;
import com.sbs.example.easytextboard.Dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public int write(int boardId, int memberId, String title, String body) {
		return articleDao.write(boardId, memberId, title, body);
	}

	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

	public int makeBoard(String name) {
		return articleDao.makeBoard(name);
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public int getDefaultBoardId() {
		List<Board> boards = articleDao.getBoards();

		return boards.get(1).id;
	}

}
