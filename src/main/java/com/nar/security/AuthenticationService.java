package com.nar.security;

import com.nar.service.UserService;
import com.nar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    public User getCurrentUser() {
        return isCurrentUserAuthenticated() ? getOrFindCurrentUser() : null;
    }

    public void authenticate(String email, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private User getOrFindCurrentUser() {
        CurrentUserHolder currentUserHolder = getCurrentUserHolder();
        if (currentUserHolder.hasNotCurrentUser()) {
            User currentUser = userService.findByEmail(getAuthentication().getName());
            currentUserHolder.setCurrentUser(currentUser);
        }
        return currentUserHolder.getCurrentUser();
    }

    public boolean isCurrentUserAuthenticated() {
        return !hasRole("ROLE_ANONYMOUS");
    }

    public boolean isCurrentUserAnonymous() {
        return !isCurrentUserAuthenticated();
    }

    private boolean hasRole(String role) {
        return getAuthentication()
                .getAuthorities().stream()
                .filter(a -> a.getAuthority().equals(role))
                .findFirst()
                .isPresent();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private CurrentUserHolder getCurrentUserHolder() {
        return applicationContext.getBean(CurrentUserHolder.class);
    }

}