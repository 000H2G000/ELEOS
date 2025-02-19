package utils;

import models.User.Utilisateur;

public class Session {
    private static Session instance;
    private Utilisateur loggedInUser;

    private Session() { }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setLoggedInUser(Utilisateur user) {
        this.loggedInUser = user;
    }

    public Utilisateur getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}
