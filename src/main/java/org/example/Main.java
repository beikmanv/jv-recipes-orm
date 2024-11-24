package org.example;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // Initialize the Hibernate SessionFactory
//        SessionFactory sessionFactory = Database.getSessionFactory();
//
//        // Seed the database with initial data
//        Database.seed(sessionFactory);
//
//        // Query using JDBC for testing and validating the data
//        String url = "jdbc:h2:mem:db1";  // Same in-memory DB as used by Hibernate
//        String user = "sa";
//        String password = "";
//
//        // Now you can use JDBC to query and validate the data that was seeded
//        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//            Statement stmt = conn.createStatement();
//
//            // Define an array of table names
//            String[] tables = {
//                    "Category", "Comment", "Image", "Ingredient", "Rating",
//                    "Recipe", "Recipe_Category", "Recipe_Ingredient", "Tag", "UserChef"
//            };
//
//            for (String table : tables) {
//                System.out.println("Content of " + table + " table:");
//                try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
//                    ResultSetMetaData metaData = rs.getMetaData();
//                    int columnCount = metaData.getColumnCount();
//
//                    // Print column names
//                    for (int i = 1; i <= columnCount; i++) {
//                        System.out.print(metaData.getColumnName(i) + "\t");
//                    }
//                    System.out.println();
//
//                    // Print rows
//                    while (rs.next()) {
//                        for (int i = 1; i <= columnCount; i++) {
//                            System.out.print(rs.getString(i) + "\t");
//                        }
//                        System.out.println();
//                    }
//                } catch (SQLException e) {
//                    System.out.println("Error reading table " + table + ": " + e.getMessage());
//                }
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            sessionFactory.close(); // Don't forget to close the SessionFactory
//        }
        /*

        // Initialize the Hibernate SessionFactory (used for database initialization)
        SessionFactory sessionFactory = Database.getSessionFactory();

        // Seed the database with initial data (this step is to populate tables if required)
        Database.seed(sessionFactory);

        // Initialize RecipeRepository
        RecipeRepository recipeRepository = new RecipeRepository(sessionFactory);

        // Example: Find all recipes with a rating of at least 4
        List<Recipe> recipesWithRating = recipeRepository.findRecipesWithRatingAtLeast(4);

        // Print the titles of the recipes found
        System.out.println("Recipes with rating >= 4:");
        for (Recipe recipe : recipesWithRating) {
            System.out.println(recipe.getTitle());
        }

        // Close the session factory after use
        recipeRepository.close();

         */

//        // Configure Hibernate and build the session factory
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml") // Ensure hibernate.cfg.xml is properly configured
//                .addAnnotatedClass(Recipe.class)
//                .addAnnotatedClass(Ingredient.class)
//                .addAnnotatedClass(Category.class)
//                .addAnnotatedClass(Tag.class)
//                .addAnnotatedClass(UserChef.class)
//                .buildSessionFactory();
//
//        try {
//            // Seed the database with sample data
//            Database.seed(sessionFactory);
//
//            // Instantiate the RecipeRepository
//            RecipeRepository recipeRepository = new RecipeRepository(sessionFactory);
//
//            // Test queries
//            System.out.println("Recipes with rating >= 4:");
//            List<Recipe> highRatedRecipes = recipeRepository.findRecipesWithRatingAtLeast(4);
//            for (Recipe recipe : highRatedRecipes) {
//                System.out.println(recipe.getTitle() + " - Rating: " + recipe.getRating());
//            }
//
//            System.out.println("\nRecipes with ingredient 'Tomato':");
//            List<Recipe> tomatoRecipes = recipeRepository.findRecipesByIngredient("Tomato");
//            for (Recipe recipe : tomatoRecipes) {
//                System.out.println(recipe.getTitle());
//            }
//
//            System.out.println("\nRecipes in category 'Appetizers':");
//            List<Recipe> appetizers = recipeRepository.findRecipesByCategory("Appetizers");
//            for (Recipe recipe : appetizers) {
//                System.out.println(recipe.getTitle());
//            }
//
//            System.out.println("\nRecipes with tag 'Vegetarian':");
//            List<Recipe> vegetarianRecipes = recipeRepository.findRecipesByTag("Vegetarian");
//            for (Recipe recipe : vegetarianRecipes) {
//                System.out.println(recipe.getTitle());
//            }
//
//            recipeRepository.close();
//        } finally {
//            // Ensure the SessionFactory is closed
//            if (sessionFactory != null) {
//                sessionFactory.close();
//            }
//        }
        // Initialize the session factory (assuming it's already configured)
        // Configure Hibernate and build the session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Ensure hibernate.cfg.xml is properly configured
                .addAnnotatedClass(Recipe.class)
                .addAnnotatedClass(Ingredient.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Tag.class)
                .addAnnotatedClass(UserChef.class)
                .buildSessionFactory();

        try {
            // Seed the database with sample data
            Database.seed(sessionFactory);

            // Instantiate the RecipeRepository
            RecipeRepository recipeRepository = new RecipeRepository(sessionFactory);

            // --- Inserting new data ---
            // Insert a new Ingredient
            Ingredient newIngredient = new Ingredient("Cucumber", 2, "pieces");
            recipeRepository.insertIngredient(newIngredient);

            // Insert a new Category
            Category newCategory = new Category("Salads");
            recipeRepository.insertCategory(newCategory);

            // Insert a new Tag
            Tag newTag = new Tag("Fresh");
            recipeRepository.insertTag(newTag);

            // Insert a new Recipe
            Recipe newRecipe = new Recipe("Cucumber Salad", "10 mins", "5 mins", "Mix cucumber with dressing", "Easy", 4, 2, new UserChef("chef@example.com", "password", "admin", "chefUser"));
            newRecipe.addIngredient(newIngredient);
            newRecipe.addCategory(newCategory);
            newRecipe.addTag(newTag);
            recipeRepository.insertRecipe(newRecipe);

            // --- Updating existing data ---
            // Rename an Ingredient
            recipeRepository.renameIngredient(1, "Sliced Cucumber");

            // Rename a Category
            recipeRepository.renameCategory(1, "Healthy Salads");

            // Add a new ingredient to an existing recipe
            // Find the recipe by title
            Recipe recipeToUpdate = recipeRepository.findRecipesByTitle("Cucumber Salad").get(0);

            // Create a new ingredient for the recipe
            Ingredient newIngredientForRecipe = new Ingredient("Olive Oil", 50, "ml");

            // Add the new ingredient to the recipe (pass the recipe ID instead of the recipe object)
            recipeRepository.addIngredientToRecipe(recipeToUpdate.getId(), newIngredientForRecipe);


            // --- Retrieving data ---
            System.out.println("\nRecipes with rating >= 4:");
            List<Recipe> highRatedRecipes = recipeRepository.findRecipesWithRatingAtLeast(4);
            for (Recipe recipe : highRatedRecipes) {
                System.out.println(recipe.getTitle() + " - Rating: " + recipe.getRating());
            }

            System.out.println("\nRecipes with ingredient 'Tomato':");
            List<Recipe> tomatoRecipes = recipeRepository.findRecipesByIngredient("Tomato");
            for (Recipe recipe : tomatoRecipes) {
                System.out.println(recipe.getTitle());
            }

            System.out.println("\nRecipes in category 'Appetizers':");
            List<Recipe> appetizers = recipeRepository.findRecipesByCategory("Appetizers");
            for (Recipe recipe : appetizers) {
                System.out.println(recipe.getTitle());
            }

            System.out.println("\nRecipes with tag 'Vegetarian':");
            List<Recipe> vegetarianRecipes = recipeRepository.findRecipesByTag("Vegetarian");
            for (Recipe recipe : vegetarianRecipes) {
                System.out.println(recipe.getTitle());
            }

            recipeRepository.close();
        } finally {
            // Ensure the SessionFactory is closed
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

}
