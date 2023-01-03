package org.example.dao;

import org.example.model.Person;

import java.util.Collection;

public interface People {
    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person update(Person person);
    Boolean deleteById(int id);

}
