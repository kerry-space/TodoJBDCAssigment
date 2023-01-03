package org.example.dao;

import org.example.model.Person;

import java.util.Optional;

public interface PersonDAO extends BaseDao<Person>{


    Person findById(Integer id);

    //optional class is utlil which checks if null or not



    Optional<Person> findByEmail(String email);
}
