package com.nar.repository;

import com.nar.model.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

    @Override
    List<Person> findAll();

}
