package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;

    @NotNull
    private String name;

    // Many-to-many relationship: Tag -> many Recipes
    @ManyToMany(mappedBy = "tags")  // "tags" is the field in Recipe
    private Set<Recipe> recipes = new HashSet<>();  // Initializes the Set to avoid null pointers


    // Constructor
    public Tag(String name) {
        this.name = name;
        this.recipes = new HashSet<>(); // Initialize the Set of recipes
    }

    // Getter for recipes (returns all recipes associated with this tag)
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    // Method to add a recipe to the tag
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe); // Adds the recipe to this tag's set
    }

    public int getiD() {
        return iD;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

}
