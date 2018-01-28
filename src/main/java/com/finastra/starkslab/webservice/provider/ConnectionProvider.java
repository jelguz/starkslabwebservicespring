package com.finastra.starkslab.webservice.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String className = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/starkslabdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	//jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	private static String username = "root";
	private static String password = "root";
	
	public ConnectionProvider() {
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
