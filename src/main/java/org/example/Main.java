package org.example;

import jakarta.persistence.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Configure Hibernate to set up the schema and database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Populate some data (optional, for testing)
        em.createNativeQuery("INSERT INTO Category (iD, name) VALUES (1, 'Appetizers')").executeUpdate();
        em.createNativeQuery("INSERT INTO Category (iD, name) VALUES (2, 'Main Course')").executeUpdate();
        em.getTransaction().commit();

        // Query using the same connection
        String url = "jdbc:h2:mem:db1";  // Same in-memory DB as used by Hibernate
        String user = "sa";
        String password = "";

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
            em.close();
            emf.close();
        }
    }
}
