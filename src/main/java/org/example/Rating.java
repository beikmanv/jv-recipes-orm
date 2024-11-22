package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 *
 * id, value (e.g. 1-5 stars), date rated, associated recipe, rater (User)
 * */
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;

    @Column(name = "rating_value")
    private int ratingValue;
    private LocalDateTime ratingDate;

    private String associatedRecipe;
    @ManyToOne
    private UserChef rater;

    public Rating() {
    }


}
