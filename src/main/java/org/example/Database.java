package org.example;

import org.hibernate.SessionFactory;
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

    public static void seed(SessionFactory sessionFactory) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create and persist categories
            Category appetizers = new Category("Appetizers");
            Category mainCourse = new Category("Main Course");
            session.persist(appetizers);
            session.persist(mainCourse);

            // Create and persist ingredients
            Ingredient peas = new Ingredient("Peas", 100, "grams");
            Ingredient chicken = new Ingredient("Chicken", 500, "grams");
            Ingredient garlic = new Ingredient("Garlic", 1, "clove");

            session.persist(peas);
            session.persist(chicken);
            session.persist(garlic);

            // Flush and refresh to get generated IDs
            session.flush(); // Synchronize the session with the database
            session.refresh(peas); // Retrieve the generated ID for 'peas'
            session.refresh(chicken); // Retrieve the generated ID for 'chicken'
            session.refresh(garlic); // Retrieve the generated ID for 'garlic'

            // Create and persist a new recipe
            Recipe recipe = new Recipe("Chicken and Peas", "Delicious chicken with peas and garlic");
            recipe.addIngredient(peas);
            recipe.addIngredient(chicken);
            recipe.addIngredient(garlic);
            session.persist(recipe);

            // Optionally, create more recipes or ingredients as needed
            Recipe anotherRecipe = new Recipe("Garlic Chicken", "Simple garlic chicken");
            anotherRecipe.addIngredient(chicken);
            anotherRecipe.addIngredient(garlic);
            session.persist(anotherRecipe);

            // Commit the transaction
            session.getTransaction().commit();
        }
    }
}
