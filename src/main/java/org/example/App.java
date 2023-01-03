package org.example;

import org.example.controller.Controller;
import org.example.dao.PersonDAO;
import org.example.dao.impl.AppUserDAOCollection;
import org.example.dao.impl.PeopleJDBC;
import org.example.dao.impl.PersonDAOCollection;
import org.example.dao.impl.TodoItemsJDBC;
import org.example.model.AppRole;
import org.example.model.AppUser;
import org.example.model.Person;
import org.example.model.TodoItem;
import org.example.view.ConsoleUi;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //Controller controller = new Controller();
        //controller.doMainMenu();

        AppUser appUser = new AppUser("kerry","LOVELOVE", AppRole.ROLE_APP_USER,true, LocalDate.parse("2023-01-01"));

        PeopleJDBC peopleJDBC = new PeopleJDBC();
        Person person = new Person(1,"kerry", "space", "kerry@gmail.com",appUser);
        //Person person1 = peopleJDBC.create(person);
        //Person person2 = peopleJDBC.create(new Person("sandra", "love","sandra@gmail.com",appUser));


        //Collection<Person> personCollection = peopleJDBC.findAll();
        //personCollection.forEach(person -> System.out.println(person));

       // Person person = peopleJDBC.findById(3);
        //System.out.println(person);

       // Collection<Person> personCollection = peopleJDBC.findByName("sandra");
       // personCollection.forEach(person -> System.out.println(person));

       /* Person person = peopleJDBC.findById(1);
        person.setFirstName("One");

        Person updatePerson = peopleJDBC.update(person);
        System.out.println(updatePerson);*/

       //boolean res = peopleJDBC.deleteById(1);
        //System.out.println(res);


        TodoItemsJDBC todoItemsJDBC = new TodoItemsJDBC();
       // Person person = peopleJDBC.findById(1);
        /*if(person != null){
            TodoItem todoItem = todoItemsJDBC.create(new TodoItem("building app", "im building a dapp app",LocalDate.parse("2023-01-02"),true,person));
            System.out.println(todoItem);
        }*/


        //Collection<TodoItem> todoItemCollection = todoItemsJDBC.findAll();
        //todoItemCollection.forEach(todoItem -> System.out.println(todoItem));

       //TodoItem todoItem = todoItemsJDBC.findById(1);
        //System.out.println(todoItem);

       /* Collection<TodoItem> todoItemCollection = todoItemsJDBC.findByDoneStatus(true);
        if(todoItemCollection != null){
            todoItemCollection.forEach(todoItem -> System.out.println(todoItem));
        }*/
       /* TodoItem todoItem = todoItemsJDBC.findById(1);
        todoItem.setTitle("riseLove");
        TodoItem todoItemUpdate = todoItemsJDBC.update(todoItem);
        System.out.println(todoItemUpdate);*/

       /* Collection<TodoItem> todoItemCollection = todoItemsJDBC.findByAssignee(2);
        if (todoItemCollection != null){
            todoItemCollection.forEach(todoItem -> System.out.println(todoItem));
        }*/

        /*boolean deletedStatus = todoItemsJDBC.deleteById(1);
        System.out.println(deletedStatus);*/


       /*Collection<TodoItem> todoItemCollection = todoItemsJDBC.findByAssignee(person);
        if(todoItemCollection != null){
            todoItemCollection.forEach(todoItem -> System.out.println(todoItem));
        }*/

        TodoItem todoItem = todoItemsJDBC.create(new TodoItem("test","love is test",LocalDate.parse("2023-01-03")));
        System.out.println(todoItem);

        /*Collection<TodoItem> todoItemCollection = todoItemsJDBC.findByUnassignedTodoItems();
        if(todoItemCollection != null){
            todoItemCollection.forEach(todoItem -> System.out.println(todoItem));
        }*/

    }
}
