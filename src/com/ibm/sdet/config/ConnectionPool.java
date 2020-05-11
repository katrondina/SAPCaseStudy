package com.ibm.sdet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibm.sdet.config.ConnectionPool;

public class ConnectionPool {
	private static ConnectionPool connectionPool;
	private ConnectionPool() {}
	
	static {
		try { Class.forName("com.mysql.cj.jdbc.Driver"); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
	
	public static ConnectionPool getInstance() {
		if(connectionPool == null) { connectionPool = new ConnectionPool(); }
		return connectionPool;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		String userNameDB = "root";
		String passwordDB = "1234";
		String databaseURL = "jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true";
		
		try {
			connection = DriverManager.getConnection(databaseURL,userNameDB,passwordDB);
		} catch (SQLException e) { e.printStackTrace(); }
		return connection;
	}
	
	public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) { resultSet.close(); }
			closeConnection(connection, preparedStatement, resultSet);
		} catch (SQLException e) {}
	}
	
	public void closeConnection(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) { preparedStatement.close(); }
			if (connection != null) { connection.close(); }
		} catch (SQLException e) {}
	}
}
