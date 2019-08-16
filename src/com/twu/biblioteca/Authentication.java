package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Authentication {
    private Optional<User> currentUser = Optional.empty();
    private final Users users;


    public Authentication( Users users) {
        this.users = users;
    }

    public void login(String userId, String password) {
        for (User userInCollection : users.getUsers()) {
            if (userInCollection.userId.equals(userId) && userInCollection.password.equals(password)) {
                currentUser = Optional.of(userInCollection);
                break;
            }
        }
    }

    public boolean isLoggedIn(){
        return currentUser.isPresent();
    }

    public void logout() {
        currentUser = Optional.empty();
    }
}
