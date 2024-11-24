package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import java.util.List;

public class RecipeRepository {

    private SessionFactory sessionFactory;

    public RecipeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Find recipes by title
    public List<Recipe> findRecipesByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Recipe r WHERE r.title = :title";
            Query<Recipe> query = session.createQuery(hql, Recipe.class);
            query.setParameter("title", title);
            return query.getResultList();
        }
    }

    // Find recipes with a rating greater than or equal to the given rating
    public List<Recipe> findRecipesWithRatingAtLeast(int minRating) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Recipe r WHERE r.rating >= :minRating";
            Query<Recipe> query = session.createQuery(hql, Recipe.class);
            query.setParameter("minRating", minRating);
            return query.getResultList();
        }
    }

    // Find recipes that contain a specific ingredient
    public List<Recipe> findRecipesByIngredient(String ingredientName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.name = :ingredientName";
            Query<Recipe> query = session.createQuery(hql, Recipe.class);
            query.setParameter("ingredientName", ingredientName);
            return query.getResultList();
        }
    }

    // Find recipes that have a specific tag
    public List<Recipe> findRecipesByTag(String tagName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT r FROM Recipe r JOIN r.tags t WHERE t.name = :tagName";
            Query<Recipe> query = session.createQuery(hql, Recipe.class);
            query.setParameter("tagName", tagName);
            return query.getResultList();
        }
    }

    // Find recipes in a specific category
    public List<Recipe> findRecipesByCategory(String categoryName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT r FROM Recipe r JOIN r.categories c WHERE c.name = :categoryName";
            Query<Recipe> query = session.createQuery(hql, Recipe.class);
            query.setParameter("categoryName", categoryName);
            return query.getResultList();
        }
    }

    // Insert a new ingredient
    public void insertIngredient(Ingredient ingredient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ingredient);
            transaction.commit();
        }
    }

    // Insert a new category
    public void insertCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        }
    }

    // Insert a new tag
    public void insertTag(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(tag);
            transaction.commit();
        }
    }

    // Insert a new recipe
    public void insertRecipe(Recipe recipe) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(recipe);
            transaction.commit();
        }
    }

    // Rename an ingredient
    public void renameIngredient(int ingredientId, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Ingredient ingredient = session.get(Ingredient.class, ingredientId);
            if (ingredient != null) {
                Transaction transaction = session.beginTransaction();
                ingredient.setName(newName); // Update the name
                session.update(ingredient);
                transaction.commit();
            }
        }
    }

    // Rename a category
    public void renameCategory(int categoryId, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                Transaction transaction = session.beginTransaction();
                category.setName(newName); // Update the name
                session.update(category);
                transaction.commit();
            }
        }
    }

    // Add a new ingredient to an existing recipe
    public void addIngredientToRecipe(int recipeId, Ingredient ingredient) {
        try (Session session = sessionFactory.openSession()) {
            Recipe recipe = session.get(Recipe.class, recipeId);
            if (recipe != null) {
                Transaction transaction = session.beginTransaction();
                recipe.addIngredient(ingredient); // Add the ingredient
                session.saveOrUpdate(recipe); // Save the updated recipe
                transaction.commit();
            }
        }
    }

    // Close session factory when done
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
