package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/careerhubjob";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Root";

    
    public static Connection getConnection() throws SQLException {
    	
    	 try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
         } catch (ClassNotFoundException | SQLException e) {
             throw new SQLException("Failed to establish a database connection.", e);
         }

    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
