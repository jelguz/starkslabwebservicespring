package com.finastra.starkslab.webservice.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String className = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://mancswcbman0128:3306/starkslabdb";
	private static String username = "remoteuser";
	private static String password = "remoteuser";
	
	public ConnectionProvider() {
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
