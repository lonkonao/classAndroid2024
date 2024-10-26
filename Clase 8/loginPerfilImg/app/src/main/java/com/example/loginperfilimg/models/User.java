package com.example.loginperfilimg.models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String role;
    private String profileImageUrl;

    // Constructor vacío necesario para Firebase
    public User() {
    }

    // Constructor con todos los campos
    public User(String userId, String name, String email, String role, String profileImageUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    // Setters (opcional, si necesitas modificar los valores)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
