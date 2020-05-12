package com.ibm.sdet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ibm.sdet.config.ConnectionPool;

public class FileLoaderInsert {
	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	public static void createSAPTbl() throws SQLException{
		String createTblQuery = "create table sap("
				+ "id int(16) auto_increment unique,"
				+ "name varchar(100) not null,"
				+ "city varchar(100),"
				+ "age int(12),"
				+ "monthlyIncome double(20),"
				+ "occupation varchar(100),"
				+ "otherNote varchar(200),"
				+ "status varchar(8) not null default 'PENDING',"
				+ "PRIMARY KEY(id))";
		Connection connection = connectionPool.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(createTblQuery);
		preparedStatement.execute();
	}

	public static void insertBeneficiary(List<Beneficiary> beneficiaries) {
		System.out.println("Inserting beneficiary to database...");
		String query = "insert into sap("
				+ "name, city, age, monthlyIncome, occupation, otherNote, status)"
				+ "value(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;

		for (Beneficiary ben : beneficiaries) {
			try {
				Connection connection = connectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, ben.getName());
				preparedStatement.setString(2, ben.getCity());
				preparedStatement.setLong(3, ben.getAge());
				preparedStatement.setDouble(4, ben.getMonthlyIncome());
				preparedStatement.setString(5, ben.getOccupation());
				preparedStatement.setString(6, ben.getOtherNote());
				preparedStatement.setString(7, ben.getStatus());
				preparedStatement.execute();
				//System.out.println("Beneficiary added.");
			} catch (Exception e) {
				System.out.println("Please check your connection string");
			}
		}
		System.out.println("Beneficiaries added.");
	}
	
	public static void testConnection() {
		Connection connection = connectionPool.getConnection();
		System.out.println(connection);
	}
}
