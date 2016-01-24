package com.nar.util;

import com.nar.model.Person;
import com.nar.model.User;
import com.nar.model.UserRole;
import com.nar.repository.PersonRepository;
import javax.annotation.PostConstruct;

import com.nar.repository.UserRepository;
import com.nar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulator {

    private PersonRepository personRepository;

    private UserService userService;
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    public DatabasePopulator(PersonRepository personRepository, UserService userService, ShaPasswordEncoder shaPasswordEncoder) {
        this.personRepository = personRepository;
        this.userService = userService;
        this.shaPasswordEncoder = shaPasswordEncoder;
    }

    @PostConstruct
    void init()
    {
        personRepository.save(new Person("erim", "erturk"));
        personRepository.save(new Person("seval", "erturk"));
        personRepository.save(new Person("dafni", "erturk"));

        String encodePassword = shaPasswordEncoder.encodePassword("123qwe", null);

        User user = new User("user@mail.com", encodePassword);
        User admin = new User("admin@mail.com", encodePassword);
        admin.setRole(UserRole.ADMIN);

        userService.save(user);
        userService.save(admin);

    }

}
