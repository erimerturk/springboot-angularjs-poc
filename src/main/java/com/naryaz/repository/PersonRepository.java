package com.naryaz.repository;

import com.naryaz.model.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

    @Override
    List<Person> findAll();

}
