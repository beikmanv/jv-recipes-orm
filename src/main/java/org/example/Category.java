package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import javax.annotation.processing.Generated;

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;
    @NotNull
    private String name;

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

    public Category() {}
}
