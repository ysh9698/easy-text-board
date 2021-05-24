package com.sbs.example.easytextboard.container;

import java.util.Scanner;

import com.sbs.example.easytextboard.Dao.ArticleDao;
import com.sbs.example.easytextboard.Dao.MemberDao;
import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Scanner scanner;
	public static Session session;
	public static ArticleService articleService;
	public static MemberService memberService;
	private static ArticleDao articleDao;
	private static MemberDao memberDao;
	public static MemberController memberController;
	public static ArticleController articleController;
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}
