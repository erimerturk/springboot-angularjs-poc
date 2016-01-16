package com.naryaz.util;

import com.naryaz.model.Person;
import com.naryaz.repository.PersonRepository;
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
