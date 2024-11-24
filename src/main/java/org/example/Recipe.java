package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * id, title, description, instructions, preparation time, cooking time
 * , servings, difficulty level, rating, list of ingredients,
 * list of categories, creator (User), date created, last modified
 * */
@Entity
public class Recipe {

    public Recipe() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @NotNull
    private String instructions;
    private String prepTime;
    private String cookingTime;
    private int servings;
    private String difficultyLevel;
    private int rating;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Recipe_Ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"recipe_id", "ingredient_id"})
    )
    private List<Ingredient> ingredients;

    @ManyToMany
    private  List<Category> categories;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserChef user;

    private LocalDateTime createdDt;
    private LocalDateTime lastModifiedDt;

    // Define the relationship between Recipe and Tag
    @ManyToMany
    @JoinTable(
            name = "Recipe_Tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    // Constructor
    public Recipe(String title, String cookingTime, String prepTime, String description,
                  String difficultyLevel, int rating, int servings, UserChef user) {
        this.title = title;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.servings = servings;
        this.user = user;
        this.ingredients = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    //User to be introduced

    // Method to add ingredients to the recipe
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    // Method to add categories to the recipe
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public UserChef getUser() {
        return user;
    }

    public void setUser(UserChef user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public LocalDateTime getLastModifiedDt() {
        return lastModifiedDt;
    }

    public void setLastModifiedDt(LocalDateTime lastModifiedDt) {
        this.lastModifiedDt = lastModifiedDt;
    }

    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.tags.add(tag);
        // Assuming that Tag has a method to add a Recipe to its list
        tag.getRecipes().add(this); // Link the recipe back to the tag
    }

}
