package com.aspire.BlogWebApp;

import java.util.Scanner;

public class About {
	Scanner scanner = new Scanner(System.in);
	public void aboutBlog() {
		System.out.print("\n\n\n\t >>>>>>\t This is a console based blog application mini project\t <<<<<< \n\n\n Thanks for visting press E for Exit ");
		String option = scanner.nextLine();
		if (option.toLowerCase() == "e") {
			System.exit(0);
		}
		else {
			System.out.print("Invalid input");
			PasswordManagement.options();
		}
	}
}
