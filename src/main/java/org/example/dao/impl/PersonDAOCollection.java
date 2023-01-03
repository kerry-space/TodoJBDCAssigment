package org.example.dao.impl;

import org.example.dao.impl.Sequencer.PersonIdSequencer;
import org.example.dao.PersonDAO;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOCollection implements PersonDAO {
    //field
    List<Person> personList;

    //Singleton Design Pattern step2: define a static field with class type
    private static PersonDAOCollection instance;

    //Singleton Design Pattern step1: one private constructor
    private PersonDAOCollection(){
        //initialing ones call PersonDaoCollection class
        personList = new ArrayList<>();
    }

    //Override methods implements interface

    //Singleton Design Pattern step3: define a static method with if condition to check the object is null or not
    public  static PersonDAOCollection getInstance(){
        if (instance == null) instance = new PersonDAOCollection();
        return instance;
    }
    @Override
    public Person persist(Person person) {
        if(person == null) throw new IllegalArgumentException("person is null");
        person.setId(PersonIdSequencer.nextId());
        personList.add(person);
        return person;
    }

    @Override
    public Person findById(Integer id) {
       if (id == 0) throw new IllegalArgumentException("id is empty");
       for (Person person : personList){
           if(person.getId() == id){
               return person;
           }
       }
       return null;
    }



    @Override
    public Optional<Person> findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email is null");
        for (Person person : personList){
            if(person.getEmail().equalsIgnoreCase(email)){
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }



    @Override
    public ArrayList<Person> findAll() {
        //convert our list to arrayList and return arrayList
        ArrayList<Person> arrayist = new ArrayList<>(personList);
        return arrayist;
    }



    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("id is empty");
        for (Person person : personList){
            if(person.getId() == id){
                personList.remove(person);
            }
        }
    }


}
