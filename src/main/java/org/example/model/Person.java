package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {


    //Fields
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    private List<TodoItem> todoItems;

    //constructors
    public Person(){

    }


    public Person(Integer id) {
        this.id = id;
    }

    public  Person(String firstName, String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(Integer id, String firstName, String lastName ){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName, String email, AppUser credentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.credentials = credentials;
    }

    public Person(int id, String firstName, String lastName, String email, AppUser credentials){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        //setTodoItems(assignedTodos);
        setCredentials(credentials);
    }



    //Getters & Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
       if(id == null) throw new RuntimeException("id was null");
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null) throw  new IllegalArgumentException("firstname para was null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null) throw  new IllegalArgumentException("lastName para was null");
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(email == null) throw new IllegalArgumentException("email para was null");
        this.email = email;
    }

    public AppUser getCredentials() {
        return credentials;
    }

    public void setCredentials(AppUser credentials) {
        if(credentials == null) throw new IllegalArgumentException("credentials was null");
        this.credentials = credentials;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> assignedTodos) {
        //if list is empty instantiate new arrayList
        if(todoItems == null)  this.todoItems = new ArrayList<>();
        this.todoItems = todoItems;
    }

    //method

    //equals override method to compare based on your setup.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
