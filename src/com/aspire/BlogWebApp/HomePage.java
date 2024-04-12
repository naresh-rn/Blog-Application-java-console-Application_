package com.aspire.BlogWebApp;

import java.util.*;
import java.sql.*;

public class HomePage {
	public static void main(String[] args) throws SQLException {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("\t\t > > > > > >  Welcome to the blog Application< < < < < < \t\t\n\n\n");
				
						Scanner scanner1 = new Scanner(System.in) ;
						System.out.print("\n\t1. Admin Access.\n\t2. User Access.\n\t3. Password Management \n\t4. About\n\t5. Exit\n\n Enter Your option : ");
						int option1 = scanner1.nextInt();
						if (option1 == 1) {
							Admin admin = new Admin();
						
							
							
							admin.adminAccess();
						}
						else if (option1 == 2) {
							UserSection userSection = new UserSection();
							userSection.userAccess();
						}
						else if (option1 == 3) {
							PasswordManagement passwordManagement = new PasswordManagement();
							passwordManagement.passwordManagement();
						}
						else if (option1 == 4) {
							About about = new About();
							about.aboutBlog();
						}
						else if (option1 == 5) {
							System.exit(0);
						}
						else {
							System.out.print(" Invalid input\n");
							PasswordManagement.options();
						}
						scanner1.close();
				}
				
			}
}
			