package com.twu.biblioteca;

import java.util.List;

public class User {
    private String userId;
    private String password;
    public String email;
    private String phone;

    public User(String userId, String password, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
