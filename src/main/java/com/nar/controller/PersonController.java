package com.nar.controller;

import com.nar.model.Person;
import com.nar.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value="", method= RequestMethod.GET)
    public List<Person> persons() {
        return personRepository.findAll();
    }
    @RequestMapping(value="", method=RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Person find(@PathVariable("id") Long id) {
        return personRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        personRepository.delete(id);
    }
}
