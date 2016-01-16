package com.nar.util;

import com.nar.model.Person;
import com.nar.repository.PersonRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulator {

    private PersonRepository personRepository;

    @Autowired
    public DatabasePopulator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    void init()
    {
        personRepository.save(new Person("erim", "erturk"));
        personRepository.save(new Person("seval", "erturk"));
        personRepository.save(new Person("dafni", "erturk"));
    }

}
