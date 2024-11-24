package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.JdbcSettings.*;

public class Database {

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver") // H2 in-memory database
                .setProperty(JAKARTA_JDBC_URL, "jdbc:h2:mem:db1")   // In-memory database URL
                .setProperty("hibernate.hikari.maximumPoolSize", "20")
                .setProperty(JAKARTA_JDBC_USER, "sa")   // Default username
                .setProperty(JAKARTA_JDBC_PASSWORD, "") // Default password
                .setProperty("hibernate.hbm2ddl.auto", "update") // <-- Add this line to generate schema
                .addAnnotatedClass(Recipe.class)
                .addAnnotatedClass(UserChef.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Image.class)
                .addAnnotatedClass(Ingredient.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(Tag.class)
                .setProperty(SHOW_SQL, TRUE.toString()) // SQL formatting options
                .setProperty(FORMAT_SQL, TRUE.toString())
                .setProperty(HIGHLIGHT_SQL, TRUE.toString())
                .buildSessionFactory();
    }

    // Seed the database with sample data
    public static void seed(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Insert sample categories
            Category category1 = new Category("Appetizers");
            Category category2 = new Category("Main Courses");
            Category category3 = new Category("Desserts");
            session.save(category1);
            session.save(category2);
            session.save(category3);

            // Insert sample ingredients
            Ingredient ingredient1 = new Ingredient("Tomato", 5, "pieces");
            Ingredient ingredient2 = new Ingredient("Cheese", 200, "grams");
            Ingredient ingredient3 = new Ingredient("Garlic", 3, "cloves");
            Ingredient ingredient4 = new Ingredient("Bread", 2, "slices");
            Ingredient ingredient5 = new Ingredient("Sugar", 100, "grams");
            session.save(ingredient1);
            session.save(ingredient2);
            session.save(ingredient3);
            session.save(ingredient4);
            session.save(ingredient5);

            // Insert sample tags
            Tag vegetarian = new Tag("Vegetarian");
            Tag quick = new Tag("Quick");
            Tag dessert = new Tag("Dessert");
            session.save(vegetarian);
            session.save(quick);
            session.save(dessert);

            // Insert sample user
            UserChef userChef = new UserChef("chef@example.com", "password", "admin", "chefUser");
            session.save(userChef);

            // Insert sample recipes
            Recipe recipe1 = new Recipe("Tomato Soup", "20 mins", "10 mins", "Cook tomatoes and garlic", "Easy", 4, 2, userChef);
            recipe1.addIngredient(ingredient1);
            recipe1.addIngredient(ingredient3);
            recipe1.addCategory(category1);
            recipe1.addTag(vegetarian); // Add Tag
            session.save(recipe1);

            Recipe recipe2 = new Recipe("Cheese Sandwich", "10 mins", "5 mins", "Put cheese between bread", "Easy", 5, 1, userChef);
            recipe2.addIngredient(ingredient2);
            recipe2.addIngredient(ingredient4);
            recipe2.addCategory(category1);
            recipe2.addTag(quick); // Add Tag
            session.save(recipe2);

            Recipe recipe3 = new Recipe("Garlic Bread", "15 mins", "5 mins", "Toast bread with garlic and butter", "Medium", 4, 2, userChef);
            recipe3.addIngredient(ingredient3);
            recipe3.addIngredient(ingredient4);
            recipe3.addCategory(category1);
            recipe3.addTag(quick); // Add Tag
            session.save(recipe3);

            Recipe recipe4 = new Recipe("Sugar Cookies", "30 mins", "15 mins", "Bake cookies with sugar", "Medium", 5, 10, userChef);
            recipe4.addIngredient(ingredient5);
            recipe4.addCategory(category3);
            recipe4.addTag(dessert); // Add Tag
            session.save(recipe4);

            // Commit transaction
            transaction.commit();
            System.out.println("Database seeded successfully!");
        }

    }
}
