package org.example.view;

import org.example.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    //display menu
    public MainMenuAction displayMenu(){
        System.out.println("1.Register");
        System.out.println("2.Create Task");
        System.out.println("3.Display All Task");
        System.out.println("4.Exit");

        System.out.println("Enter one of the a number:");
        Scanner sc = new Scanner(System.in);


        switch (sc.nextInt()){
            case 1:
                return MainMenuAction.REGISTER;
            case 2:
                return MainMenuAction.CREATE_TASK;
            case 3:
                return MainMenuAction.DISPLAY_TASKS;
            default:
                return MainMenuAction.EXIT;
        }

    }

    //getString &  getNumber
    public int getNumber(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public String getString(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    //get person data
    public Person getPerson(){
        System.out.println("username:");
        String username = getString();
        System.out.println("password:");
        String password = getString();

        AppUser appUserData = new AppUser(username,password,AppRole.ROLE_APP_USER, true,LocalDate.of(2023,1,10));

        System.out.println("FirstName:");
        String firstname = getString();
        System.out.println("LastName:");
        String lastname = getString();
        System.out.println("email");
        String email = getString();

        Person personData = new Person(firstname,lastname,email, appUserData);

        return personData;
    }

    //Display person information
    public void  displayPersonInformation(Person person){

        System.out.println(person.toString());
    }

    //get todo_item data
    public TodoItem getTodoItem(){
        System.out.println("Task Title:");
        String title = getString();
        System.out.println("Task Description:");
        String taskDescription = getString();
        System.out.println("DeadLine (YYYY-MM-DD):");
        String deadline = getString();

        System.out.println("PersonId:");
        Integer personId = getNumber();


        TodoItem todoItemData = new TodoItem(title,taskDescription,LocalDate.parse(deadline));

       Person personData = new Person();
       personData.setId(personId);

        todoItemData.setAssignee(personData);


        return todoItemData;
    }


    //display all tasks
    public void displayTodoItemInformation(TodoItem todoItem){
        System.out.println(todoItem.toString());
    }

    public void displayTodoItem(List<TodoItem> todoItemsData){
        for (TodoItem todoItem : todoItemsData){
            System.out.println(todoItem);
        }
    }
    //
}
