package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToMany
    private List<Ingredient> ingredients;

    @ManyToMany
    private  List<Category> categories;

    @ManyToOne
    private UserChef user;
    private LocalDateTime createdDt;
    private LocalDateTime lastModifiedDt;

   //User to be introduced

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
}
