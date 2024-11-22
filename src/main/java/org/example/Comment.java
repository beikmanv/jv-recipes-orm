package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * id, text, date posted, author (User), associated recipe
 * */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private LocalDateTime datePosted;

    @ManyToOne
    private UserChef author;


}
