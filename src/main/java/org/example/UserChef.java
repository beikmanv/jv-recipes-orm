package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * User
 * id, username, email, password,
 * roles (admin, regular user),
 * date registered
 * */
@Entity
public class UserChef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;
    @NotNull
    @Column(unique=true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @NotNull
    private String role;
    private LocalDateTime dateRegistered;
    public UserChef(){

    }

    public UserChef(String mail, String password, String admin, String username) {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
