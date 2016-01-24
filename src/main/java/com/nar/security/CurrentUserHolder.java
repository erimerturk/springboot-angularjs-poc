package com.nar.security;

import com.nar.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class CurrentUserHolder {

    private User currentUser;

    public boolean hasNotCurrentUser() {
        return currentUser == null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
