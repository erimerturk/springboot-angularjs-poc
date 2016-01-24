package com.nar.service;

import com.nar.model.User;
import com.nar.model.dto.SignUpRequest;
import com.nar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ShaPasswordEncoder shaPasswordEncoder) {
        this.userRepository = userRepository;
        this.shaPasswordEncoder = shaPasswordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User newUser(SignUpRequest req) {
        User user = new User(req.getUsername(), shaPasswordEncoder.encodePassword(req.getPassword(), null));
        return userRepository.save(user);
    }
}
