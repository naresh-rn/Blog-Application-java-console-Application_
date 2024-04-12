package com.aspire.BlogWebApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UserSection {
	public void userSignUp(){
		try {
			Scanner scanner = new Scanner(System.in);
	
			System.out.print("Enter your name : ");
			String name = scanner.nextLine();
			System.out.print("Enter your Email : ");
			String email = scanner.nextLine();
			System.out.print("Enter your Password(numeric only) : ");
			String password = PasswordManagement.readPassword(scanner);
			String user_id = generateUserId(name);
			
			String query = "INSERT INTO user_info (name, email, password, user_id) VALUES (?, ?, ?, ?)";
			Connection connection = DataBaseConnection.getconnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
	
			
			preparedStatement.setString(1, name );
			preparedStatement.setString(2, email );
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, user_id );
				
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Insert successful!");
	        } else {
	            System.out.println("Insert failed.");
	        }
			preparedStatement.close();
	        connection.close();
	        
			System.out.println("SignUp Successfully. Your User Id : " + user_id);
			System.out.println("if yo want to 1. Login 2.Back 3.Exit  ");
			
			int options = scanner.nextInt();
			if (options == 1) {
				System.out.print("\nYour in user Login");
				userLogin();
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
	
	public void userAccess() throws SQLException {
		Scanner optionScanner = new Scanner(System.in);

		System.out.println("\n\t1. User Sign Up.\n\t2. User Log In.\n\t3. Exit");
		System.out.print("Enter Your option : \n");
		int signUpOption = optionScanner.nextInt();
		if(signUpOption == 1) {
			userSignUp();
		}
		else if (signUpOption == 2){
			userLogin();
		}
		else if (signUpOption == 3) {
			System.exit(0);
		}
	
		else {
			System.out.println("Invalid input");
		}
		optionScanner.close();
	}
	public void userLogin() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your User ID : ");
            String user_id = scanner.nextLine();
            System.out.print("Enter your Password : ");
			String password = PasswordManagement.readPassword(scanner);
			
            String query = "SELECT * FROM user_info WHERE user_id = ? AND password = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful!");
                System.out.print("\n\t1. View Blog \n\t2. Blog Post info \n\t3. Exit");
                while(true) {
	                int userOption = scanner.nextInt();
	                if (userOption == 1){
	                	BlogManagement blogManagement = new BlogManagement();
	                	blogManagement.displayBlogInfo();
	                }
	                else if (userOption == 2) {
	                	HomePage homePage = new HomePage();
	                	homePage.main(null);
	                }
	                else if(userOption == 3) {
	                	System.exit(0);
	                }
	                else {
	                	System.out.println("Invalid Option...");
	                }
                }
            } else {
                System.out.println("Login failed. Invalid email or password.");
                PasswordManagement.options();
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static String generateUserId(String name) {
        Random random = new Random();
        int randomNumber = 5000 + random.nextInt(9999);
        String user_id ="USER" + randomNumber +"@"+ name.substring(0, 4).toUpperCase();
        return user_id;
    }
}
