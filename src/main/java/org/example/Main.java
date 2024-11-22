package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Database.getSessionFactory().getSchemaManager().exportMappedObjects(true);

        String url = "jdbc:h2:mem:db1";  // Your in-memory database URL
        String user = "sa";  // Default user
        String password = "";  // Default password (blank)

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Create a Statement to run SQL queries
            Statement stmt = conn.createStatement();
            // Execute query to show tables
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            // Print the list of tables
            while (rs.next()) {
                System.out.println(rs.getString(1));  // Table names are in the first column
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}