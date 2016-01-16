package com.nar.controller;


import com.nar.model.Person;
import com.nar.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private PersonRepository personRepository;

    @Autowired
    public HomeController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/signUp")
    public String signUp(){
        return "signup";
    }

    @RequestMapping(value="/signUp", method= RequestMethod.POST)
    @ResponseBody
    public Person signUp(@RequestBody Person person) {
        return personRepository.save(person);
    }

}
