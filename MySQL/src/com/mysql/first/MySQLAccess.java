package com.mysql.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {

			connect = DriverManager.getConnection("jdbc:mysql://1localhost:3306/feedback?" + "user=root&password=root");

			statement = connect.createStatement();

			resultSet = statement.executeQuery(
					"select myuser, webpage, DATE_FORMAT(datum, \"%W %e de %M %Y\") as datum, summary, comments from feedback.comments");
			writeResultSet(resultSet);

			preparedStatement = connect
					.prepareStatement("insert into feedback.comments values (default, ?, ?, ?, ? , ?, ?)");

			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			Date utilDate = new java.util.Date();
			preparedStatement.setDate(4, new java.sql.Date(utilDate.getTime()));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement(
					"select myuser, webpage, DATE_FORMAT(datum,\"%W %e de %M %Y\") as datum, summary, comments from feedback.comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ?; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from feedback.comments");
			writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));

		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			String date = resultSet.getString("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
			System.out.println("======================================");
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
