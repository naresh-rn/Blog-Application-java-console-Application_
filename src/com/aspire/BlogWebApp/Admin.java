package com.aspire.BlogWebApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Admin {
	
	public void adminSignUp() throws SQLException{		
		try {
			Scanner adminScanner = new Scanner(System.in);
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Enter your name : ");
			String name = adminScanner.nextLine();
			System.out.print("Enter your Email: ");
			String email = adminScanner.nextLine();
			
			System.out.print("Enter your Password : ");
			String password = PasswordManagement.readPassword(scanner);
			String admin_id = generateAdminId(name);
			
			String query = "INSERT INTO admin_info (name, email, password, admin_id) VALUES (?, ?, ?, ?)";
			Connection connection = DataBaseConnection.getconnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, name );
			preparedStatement.setString(2, email );
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, admin_id );
				
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Insert successful!");
	        } else {
	        	System.out.println("Insert failed.");
	        }
			preparedStatement.close();
	        connection.close();
//	        adminScanner.close();
	        
			System.out.println("SignUp Successfully Your Admin_Id : " + admin_id+"\n 1.login 2.Back 3.Exit");
			int options = scanner.nextInt();
			if (options == 1) {
				System.out.print("\nYour in Admin Login");
				adminLogin();
			}
			else if (options == 2) {
				System.out.print("Your clicked the calling main function");
				HomePage.main(null);
			}
			else if(options == 3){
				System.exit(0);
			}
			else {
				System.out.println("Invalid input");
			}
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void adminLogin() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your Admin ID : ");
            String admin_id = scanner.nextLine();
            System.out.print("Enter your Password : ");
			String password = PasswordManagement.readPassword(scanner);

            String query = "SELECT * FROM admin_info WHERE admin_id = ? AND password = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin_id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful!");
                // You can do additional actions here upon successful login
                System.out.print("\n\t1. Upload Blog post \n\t2. Exit");
                while(true) {
	                int adminOption = scanner.nextInt();
	                if (adminOption == 1){
	                	BlogManagement blogManagement = new BlogManagement();
	                	blogManagement.uploadBlogPost();
	                }
//	                else if (adminOption == 2) {
//	                	BlogManagement blogManagement = new BlogManagement();
//	                	blogManagement.displayBlogInfo();
//	                	
//	                }
	                else if(adminOption == 2) {
	                	BlogManagement blogManagement = new BlogManagement();
	                	blogManagement.deletePost();
	                }
	                else if(adminOption == 4) {
	                	BlogManagement blogManagement = new BlogManagement();
//	                	blogManagement.updateBlog();
	                }
	                else {
	                	System.out.println("Invalid Option...");
	                }
                }
            } else {
                System.out.println("Login failed. Invalid email or password.");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void adminAccess() throws SQLException {
		Scanner optionScanner = new Scanner(System.in);

		System.out.println("\n\t1. Admin Sign Up.\n\t2. Admin Log In.\n\t3. Exit\n\n");
		System.out.print("Enter Your option : ");
		int signUpOption = optionScanner.nextInt();
		if(signUpOption == 1) {
			adminSignUp();
		}
		else if (signUpOption == 2){
			adminLogin();
		}
		else if (signUpOption == 3) {
			System.exit(0);
		}
		else {
			System.out.println("Invalid input");
		}
		optionScanner.close();
	}
	// admin id generator 
	public static String generateAdminId(String name) {
        Random random = new Random();
        int randomNumber = 100009 + random.nextInt(999999);
        String admin_id = name + "$" + randomNumber;
        return admin_id;
	}
}
