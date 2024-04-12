package com.aspire.BlogWebApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
	public static String url = "jdbc:mysql://localhost:3306/BlogWebSite";
	public static String user = "root";
	public static String passWord = "rnh2003";
	
	public static Connection getconnection() throws SQLException{	
		return DriverManager.getConnection(url, user, passWord);
	}
}