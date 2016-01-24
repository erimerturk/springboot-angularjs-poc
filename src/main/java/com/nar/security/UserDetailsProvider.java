package com.nar.security;

import com.nar.service.UserService;
import com.nar.model.User;
import com.nar.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsProvider implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + email);
        }

        return createUserDetailsFrom(user);
    }

    private UserDetails createUserDetailsFrom(User user) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(user);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        UserRole userRole = user.getRole() == null ? UserRole.USER : user.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));

        return authorities;
    }

}
