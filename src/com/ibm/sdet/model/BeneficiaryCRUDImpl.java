package com.ibm.sdet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibm.sdet.config.ConnectionPool;
import com.ibm.sdet.view.DisplayBeneficiaryList;

public class BeneficiaryCRUDImpl{
	
	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	public static Beneficiary ReadSapTbl(int idEntered) throws SQLException{
		System.out.println("Retrieving record...\n");
		ResultSet rs;
		Beneficiary beneficiary = new Beneficiary();
		String readSapTblQuery = "select * from sap where id in (?)";
		
		PreparedStatement preparedStatement = null;
		Connection connection = connectionPool.getConnection();
		preparedStatement = connection.prepareStatement(readSapTblQuery);
		preparedStatement.setInt(1, idEntered);
		rs = preparedStatement.executeQuery();
		while (rs.next()) {
			beneficiary.setId(rs.getInt(1));
			beneficiary.setName(rs.getString(2));
			beneficiary.setCity(rs.getString(3));
			beneficiary.setAge(rs.getInt(4));
			beneficiary.setMonthlyIncome(rs.getDouble(5));
			beneficiary.setOccupation(rs.getString(6));
			beneficiary.setOtherNote(rs.getString(7));
			beneficiary.setStatus(rs.getString(8));
		}
		preparedStatement.close();
		rs.close();
		connection.close();
		return beneficiary;
	}
	
	public static List<Beneficiary> ReadSapTblAll() throws SQLException{
		List<Beneficiary> ben = new ArrayList<Beneficiary>();
		int rowCounter = 0;
		System.out.println("Retrieving records...");
		ResultSet rs;
		String readSapTblAll = "select * from sap";
		PreparedStatement preparedStatement = null;
		Connection connection = connectionPool.getConnection();
		preparedStatement = connection.prepareStatement(readSapTblAll);
		rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Beneficiary benn = new Beneficiary();
			benn.setId(rs.getInt(1));
			benn.setName(rs.getString(2));
			benn.setCity(rs.getString(3));
			benn.setAge(rs.getInt(4));
			benn.setMonthlyIncome(rs.getDouble(5));
			benn.setOccupation(rs.getString(6));
			benn.setOtherNote(rs.getString(7));
			benn.setStatus(rs.getString(8));
			rowCounter = rowCounter + 1;
			ben.add(benn);
		}
		System.out.println("No of beneficiaries retrieved: "+rowCounter);
		preparedStatement.close();
		rs.close();
		connection.close();
		return ben;
	}
	
	public static void UpdateSapTbl(int idEntered, String statusEntered) throws SQLException {
		System.out.println("Updating beneficiary...");
		int rs;
		String updateSapTbl = "Update sap set status = ? where id in (?)";
		PreparedStatement preparedStatement = null;
		Connection connection = connectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSapTbl);
		preparedStatement.setString(1, statusEntered);
		preparedStatement.setInt(2, idEntered);
		rs = preparedStatement.executeUpdate();
		if(rs>0) {System.out.println("Beneficiary updated.");}
		preparedStatement.close();
		connection.close();
	}
	
	public static void InsertABeneficiary(Beneficiary ben) {
		System.out.println("Inserting beneficiary to database...");
		ResultSet rs;
		int id;
		DisplayBeneficiaryList display = new DisplayBeneficiaryList();
		String query = "insert into sap("
				+ "name, city, age, monthlyIncome, occupation, otherNote, status)"
				+ "value(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
			try {
				Connection connection = connectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, ben.getName());
				preparedStatement.setString(2, ben.getCity());
				preparedStatement.setLong(3, ben.getAge());
				preparedStatement.setDouble(4, ben.getMonthlyIncome());
				preparedStatement.setString(5, ben.getOccupation());
				preparedStatement.setString(6, ben.getOtherNote());
				preparedStatement.setString(7, ben.getStatus());
				preparedStatement.execute();
				rs = preparedStatement.getGeneratedKeys();
				if(rs.next()) {
					id = rs.getInt(1);
					System.out.println("Beneficiary added.");
					display.DisplayABeneficiary(ReadSapTbl(id));
				}
				preparedStatement.close();
				rs.close();
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Please check your connection string");
			}
	}
	
	public static void DeleteSapTbl(int idEntered) throws SQLException{
		System.out.println("Deleting beneficiary "+idEntered+".\n");
		int rs;
		String deleteSapTblQuery = "delete from sap where id in (?)";
		PreparedStatement preparedStatement = null;
		Connection connection = connectionPool.getConnection();
		preparedStatement = connection.prepareStatement(deleteSapTblQuery);
		preparedStatement.setInt(1, idEntered);
		rs = preparedStatement.executeUpdate();
		if(rs>0) {System.out.println("Beneficiary deleted.");}
		preparedStatement.close();
		connection.close();
	}
}
