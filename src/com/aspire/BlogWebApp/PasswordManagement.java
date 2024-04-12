package com.aspire.BlogWebApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PasswordManagement {

    public static void resetAdminPassword() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter admin ID:");
            String admin_Id = scanner.nextLine();
            System.out.println("Enter new password for admin:");
            String newPassword = PasswordManagement.readPassword(scanner);


            String query = "UPDATE admin_info SET password = ? WHERE admin_Id = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, admin_Id);
            preparedStatement.executeUpdate();
            System.out.println("Admin password reset successfully.");
           
            preparedStatement.close();
    		connection.close();
//    		scanner.close();
    		options();
    		
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetUserPassword() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter user ID:");
            String user_id = scanner.nextLine();
            System.out.println("Enter new password for user in Numeric values:");
            String newPassword = PasswordManagement.readPassword(scanner);

            String query = "UPDATE user_info SET password = ? WHERE user_id = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, user_id);
            preparedStatement.executeUpdate();
            
            System.out.println("User password reset successfully.");
            
    		preparedStatement.close();
    		connection.close();
    		options();
    		
        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
    }
    public void passwordManagement() {
    	Scanner scanner = new Scanner(System.in);
    
    	System.out.println("\n\t 1. Admin 2. User1\n");
    	int option = scanner.nextInt();
    	if (option == 1) {
    		resetAdminPassword();
    	}
    	else if (option == 2) {
    		resetUserPassword();
//    		System.exit(0);
    	}
    	else {
    		System.err.print("Invalid Option \t Enter 1 for restart the reset password menu: ");
    		int option1 = scanner.nextInt();
    		if (option1 == 1) {
    			passwordManagement();
    		}
    		else {
    			System.err.print("Invalid Option");
    		}
    	}
    	scanner.close();
    }
    
     public static String readPassword(Scanner scanner) {
    	System.out.print("\033[8m");
    	String password = scanner.nextLine();
    	System.out.print("\033[0m");
    	return password;
    }
    public static void options() {
    	try {
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("\n\t1. Back to Menu\n\t2. Exit\n");
	    	int option = scanner.nextInt();
	    	if (option == 1) {
	    		HomePage.main(null);
	    	}
	    	else if (option == 2) {
	    		System.out.print("Thanks");
	    		System.exit(0);
	    	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
