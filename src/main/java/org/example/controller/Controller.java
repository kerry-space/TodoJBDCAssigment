package org.example.controller;

import org.example.dao.AppUserDAO;
import org.example.dao.PersonDAO;
import org.example.dao.TodoItemDAO;
import org.example.dao.TodoItemTaskDAO;
import org.example.dao.impl.TOdoItemDAOCollection;
import org.example.dao.impl.TodoItemTaskDAOCollection;
import org.example.model.TodoItem;
import org.example.model.TodoItemTask;
import org.example.view.MainMenuAction;
import org.example.dao.impl.AppUserDAOCollection;
import org.example.dao.impl.PersonDAOCollection;
import org.example.model.AppUser;
import org.example.model.Person;
import org.example.view.ConsoleUi;

import java.util.List;

public class Controller {
    ConsoleUi ui;
    PersonDAO personDAO;
    AppUserDAO appUserDAO;

    TodoItemDAO todoItemDAO;
    TodoItemTaskDAO todoItemTaskDAO;

    public Controller(){
       ui = new ConsoleUi();
        personDAO = PersonDAOCollection.getInstance();
        appUserDAO = AppUserDAOCollection.getInstance();
        todoItemDAO = TOdoItemDAOCollection.getInstance();
        todoItemTaskDAO = TodoItemTaskDAOCollection.getInstance();
    }
    public void doMainMenu(){

        while (true){
           MainMenuAction action = ui.displayMenu();

           switch (action){
               case REGISTER:
                   doRegister();
                   break;
               case CREATE_TASK:
                   doCreateTodoItem();
                   break;
               case DISPLAY_TASKS:
                   doDisplayTodoItems();
                   break;
               case EXIT:
                   System.out.println(0);
           }
        }
    }

    public void doRegister(){

        Person personData = ui.getPerson();

        AppUser appUserData = personData.getCredentials();
        AppUser createdAppUser = appUserDAO.persist(appUserData);

        personData.setCredentials(createdAppUser);
       Person createdPerson = personDAO.persist(personData);

       ui.displayPersonInformation(createdPerson);

    }

    public void doCreateTodoItem(){
        TodoItem todoItemData = ui.getTodoItem();
        Person personData = todoItemData.getAssignee();


        Person foundPerson = personDAO.findById(personData.getId());

        todoItemData.setAssignee(foundPerson);
        TodoItem createdTodoItem = todoItemDAO.persist(todoItemData);


       ui.displayTodoItemInformation(createdTodoItem);
    }

    public void doDisplayTodoItems(){
        List<TodoItem> todoItemsList = todoItemDAO.findAll();
        ui.displayTodoItem(todoItemsList);

    }
}
