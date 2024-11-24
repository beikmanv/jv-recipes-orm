package org.example;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the Hibernate SessionFactory
        SessionFactory sessionFactory = Database.getSessionFactory();

        // Seed the database with initial data
        Database.seed(sessionFactory);

        // Query using JDBC for testing and validating the data
        String url = "jdbc:h2:mem:db1";  // Same in-memory DB as used by Hibernate
        String user = "sa";
        String password = "";

        // Now you can use JDBC to query and validate the data that was seeded
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();

            // Define an array of table names
            String[] tables = {
                    "Category", "Comment", "Image", "Ingredient", "Rating",
                    "Recipe", "Recipe_Category", "Recipe_Ingredient", "Tag", "UserChef"
            };

            for (String table : tables) {
                System.out.println("Content of " + table + " table:");
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Print column names
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(metaData.getColumnName(i) + "\t");
                    }
                    System.out.println();

                    // Print rows
                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.out.println("Error reading table " + table + ": " + e.getMessage());
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close(); // Don't forget to close the SessionFactory
        }
    }
}
