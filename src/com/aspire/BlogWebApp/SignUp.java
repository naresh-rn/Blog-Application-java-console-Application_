package com.aspire.BlogWebApp;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;


public class SignUp {
	Scanner adminScanner = new Scanner(System.in);
	Scanner scanner = new Scanner(System.in);
	Scanner optionScanner = new Scanner(System.in);
		
	public void adminSignUp() {
		try {
			System.out.print("Enter your name : ");
			String name = adminScanner.nextLine();
			System.out.print("Enter your Email: ");
			String email = adminScanner.nextLine();
			System.out.print("Enter your Password : ");
			System.out.println("");
			
			String password = adminScanner.nextLine();
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
	        adminScanner.close();
	        
			System.out.println("SignUp Successfully Your Admin_Id : " + admin_id);
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void userSignUp() throws SQLException{
		System.out.print("Enter your name : ");
		String name = scanner.nextLine();
		System.out.print("Enter your Email : ");
		String email = scanner.nextLine();
		System.out.print("Eeter your Password : ");
		int password = scanner.nextInt();
		String user_id = generateUserId(name);
		
		String query = "INSERT INTO user_info (name, email, password, user_id) VALUES (?, ?, ?, ?)";
		Connection connection = DataBaseConnection.getconnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		
		preparedStatement.setString(1, name );
		preparedStatement.setString(2, email );
		preparedStatement.setInt(3, password);
		preparedStatement.setString(4, user_id );
			
		int rowsAffected = preparedStatement.executeUpdate();
		if (rowsAffected > 0) {
            System.out.println("Insert successful!");
        } else {
            System.out.println("Insert failed.");
        }
		preparedStatement.close();
        connection.close();
        scanner.close();
        
		System.out.println("SignUp Successfully Your Admin_Id : " + user_id);
	}
	
	public void signUp() throws SQLException {
		System.out.println("\n\t1. Admin Sign Up.\n\t2. User Sign Up.\n\t3. Exit");
		System.out.print("Enter Your option : \n");
		int signUpOption = optionScanner.nextInt();
		if(signUpOption == 1) {
			adminSignUp();
		}
		else if (signUpOption == 2){
			userSignUp();
		}
		else if (signUpOption == 3) {
			System.exit(0);
		}
		else {
			System.out.println("Invalid input");
		}
		scanner.close();
	}
	
	
	// admin id generator 
	public static String generateAdminId(String name) {
        Random random = new Random();
        int randomNumber = 100009 + random.nextInt(999999);
        String admin_id = name + "$" + randomNumber;
        return admin_id;
    }
	public static String generateUserId(String name) {
        Random random = new Random();
        int randomNumber = 5000 + random.nextInt(9999);
        String user_id ="USER" + randomNumber +"@"+ name.substring(0, 3).toUpperCase();
        return user_id;
    }
	
	
}
