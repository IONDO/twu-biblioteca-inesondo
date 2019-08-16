package com.twu.biblioteca;

import java.util.Optional;

public class Authentication {
    private Optional<User> currentUser = Optional.empty();
    private final Users users;
    private User currentUserData = null;


    public Authentication(Users users) {
        this.users = users;
    }

    public void login(String userId, String password) {
        for (User userInCollection : users.getUsers()) {
            if (userInCollection.userId.equals(userId) && userInCollection.password.equals(password)) {
                currentUser = Optional.of(userInCollection);
                currentUserData = new User(userInCollection.userId, userInCollection.password);
                break;
            }
        }
    }

    public User getCurrentUserData() {
        return currentUserData;
    }

    public boolean isLoggedIn(){
        return currentUser.isPresent();
    }

    public void logout() {
        currentUser = Optional.empty();
    }
}
