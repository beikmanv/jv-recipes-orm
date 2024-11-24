package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    public Category() {};

    public int getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setiD(int iD) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Category(String mainCourse) {}
}
