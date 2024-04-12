package com.aspire.BlogWebApp;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlogManagement {

	public void displayBlogInfo() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter The Blog Id to View :");
		String blogID = scanner.nextLine();
		
		String query = "select * from BlogInfo where blogId = '"+blogID+"'";
		Connection connection = DataBaseConnection.getconnection();
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		// loop to display in console
		while (resultset.next()) {
			String blogId = resultset.getString(1);
			String title = resultset.getString(2);
			String content = resultset.getString(3);
			String author = resultset.getString(4);
			String postdate = resultset.getString(5);
			
			System.out.println("BlogId : " + blogId);
			System.out.println("Title : " + title);
			System.out.println("Content : " + content);
			System.out.println("Author : " + author);
			System.out.println("Post Date : " + postdate);
		}
		resultset.close();
		statement.close();
		connection.close();
//		scanner.close();
		
		System.out.println("If You Want see any other Posts Yes/No");
		String choice = scanner.nextLine().toLowerCase();
		if(choice == "yes") {
			displayBlogInfo();
		}
		else if (choice == "no") {
			System.out.println("Thanks you... Bye");
		}
		else {
			System.out.println("Invalid Input . Enter the valid input option");
		}
	}// method end
	
	public void updateBlogPostContent() throws SQLException {
		
		
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Enter your Blog ID: ");
			String blog_id = scanner.nextLine();
			System.out.print("Enter the Blog content to Update this "+ blog_id+" Blog ");
			String updatedContent = scanner.nextLine();
		try {	
			String query = "UPDATE blogInfo SET content = ? WHERE blogId = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updatedContent);
            preparedStatement.setString(2, blog_id);
            preparedStatement.executeUpdate();
            
            System.out.println("Blog post updated successfully.");
            
    		preparedStatement.close();
    		connection.close();
		}
		finally {
			String postDate = currentDateTime();
			String query = "UPDATE blogInfo SET postDate = ? WHERE blogId = ?";
            Connection connection = DataBaseConnection.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, postDate);
            preparedStatement.setString(2, blog_id);
            preparedStatement.executeUpdate();
            System.out.println("Date also updated successfully.");
            
    		preparedStatement.close();
    		connection.close();
		}
	}
	public void uploadBlogPost() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter your Blog Title: ");
			String title = scanner.nextLine();
			System.out.print("Enter your Blog Content : ");
			String content = scanner.nextLine();
			System.out.print("Enter your Blog Author Name : ");
			String author = scanner.nextLine();
			String postDate = currentDateTime();
	        System.out.println("Current Date and Time: " + postDate);
	        String blogId = title.substring(0,3)+"-"+blogId();
			
			String query = "INSERT INTO blogInfo (blogId, title, content, author, postDate) VALUES (?, ?, ?, ?, ?)";
			Connection connection = DataBaseConnection.getconnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, blogId );
			preparedStatement.setString(2, title );
			preparedStatement.setString(3, content);
			preparedStatement.setString(4, author );
			preparedStatement.setString(5, postDate );

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Insert successful!");
	        } else {
	            System.out.println("Insert failed.");
	        }
			preparedStatement.close();
	        connection.close();
	        
			System.out.println("SignUp Successfully Your Admin_Id : " + blogId);
			System.out.print("If you want to add any other products\t 1. Yes 2. No 3. Back");
			int option = scanner.nextInt();
			if (option == 1) {
				uploadBlogPost();
			}
			else if (option == 2){
				System.exit(0);
			}
			else {
				
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void deletePost() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter the blog ID to delete: ");
	        String blogId = scanner.nextLine();
	        
	        String query = "DELETE FROM blogInfo WHERE blog_id = '" + blogId + "'";
			Connection connection = DataBaseConnection.getconnection();
			Statement statement = connection.createStatement();
			
			int rowsAffected = statement.executeUpdate(query);
			if (rowsAffected > 0) {
                System.out.println("Row deleted successfully.");
            } else {
                System.out.println("No rows deleted. Blog ID not found.");
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	// blog management
	
	public void blogManagement() throws SQLException {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("\t1. Post a Blog. \n\t2. Edit Blog. \n\t3. Delete Blog");
			System.out.print("\n\t5. displayBlog.\n\t2. Blog.\n\t3. About\n\t4. Logout");
			int option = scanner.nextInt();
			if (option == 1) {
				uploadBlogPost();
			}
			else if (option == 2) {
				displayBlogInfo();
			}
			else if (option == 3) {
				deletePost();
			}
		}
	}
	// current date
	public static String currentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
	}
	// blogId 
	public static int blogId() {
        Random random = new Random();
        int randomNum = 1000 + random.nextInt(9999);
        return randomNum;
    }
		
	

}
