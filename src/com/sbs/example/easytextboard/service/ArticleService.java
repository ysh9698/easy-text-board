package com.sbs.example.easytextboard.service;

import com.sbs.example.easytextboard.Dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public int write(int memberId, String title, String body) {
		return articleDao.write(memberId, title, body);
	}

}
