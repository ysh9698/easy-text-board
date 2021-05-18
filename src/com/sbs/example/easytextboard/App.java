package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.controller.MemberController;

public class App {
	public void run() {
		Scanner sc = Container.scanner;
		
		MemberController memberController = new MemberController();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			System.out.println(cmd);
			
			if (cmd.equals("system exit")) {
				break;
			} else if (cmd.startsWith("member ")) {
				memberController.doCommand(cmd);
			}
		}
		
		sc.close();
	}
}
