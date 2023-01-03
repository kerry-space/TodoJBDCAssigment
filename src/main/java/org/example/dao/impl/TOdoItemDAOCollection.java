package org.example.dao.impl;

import org.example.dao.impl.Sequencer.TodoItemIdSequencer;
import org.example.dao.TodoItemDAO;
import org.example.model.TodoItem;

import java.time.LocalDate;
import java.util.*;

public class TOdoItemDAOCollection implements TodoItemDAO {

    //field
    List<TodoItem> todoItemsList;

    private static TOdoItemDAOCollection instance;

    //constructor
    private TOdoItemDAOCollection(){
        //initialing ones call AppUserDaoCollection class
        todoItemsList = new ArrayList<>();
    }

    public static  TOdoItemDAOCollection getInstance(){
        if(instance == null) instance = new TOdoItemDAOCollection();
        return instance;
    }
    @Override
    public TodoItem persist(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("todoItem is null");
        todoItem.setId(TodoItemIdSequencer.nextId());
        todoItemsList.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        if(id == 0) throw new IllegalArgumentException("id is empty");
        for (TodoItem todoItem : todoItemsList){
            if(todoItem.getId() == id){
                return todoItem;
            }
        }
        return null;
    }

    @Override
    public List<TodoItem> findAll() {
       return todoItemsList;
    }

    @Override
    public TodoItem findById(Integer id) {
        return null;
    }

    @Override
    public List<TodoItem> findAllByDoneStatus(boolean done) {
       for (TodoItem todoItem : todoItemsList){
           if(todoItem.getDone()){
               List<TodoItem> filteredDoneStatus = new ArrayList<>();
               filteredDoneStatus.add(todoItem);
               return filteredDoneStatus;
           }
       }
       return null;
    }

    @Override
    public List<TodoItem> findByTitleContains(String title) {
        if (title == null) throw new IllegalArgumentException("title is null");
        for (TodoItem todoItem: todoItemsList){
            if(todoItem.getTitle().equalsIgnoreCase(title)){
                List<TodoItem> filteredTitle = new ArrayList<>();
                filteredTitle.add(todoItem);

                return filteredTitle;
            }
        }
        return null;
    }

    @Override
    public List<TodoItem> findByPerson(int personId) {
       if (personId == 0) throw new IllegalArgumentException("person id is empty");
       for (TodoItem todoItem : todoItemsList){
           if(todoItem.getId() == personId){
               List<TodoItem> filteredByPerson = new ArrayList<>();
               filteredByPerson.add(todoItem);
               return filteredByPerson;
           }
       }
       return null;
    }

    @Override
    public List<TodoItem> findByDeadlineBefore(LocalDate date) {
        LocalDate today = LocalDate.now();
        if(date == null) throw new IllegalArgumentException("date is null");
        for(TodoItem todoItem : todoItemsList){
           //The function returns true if this date is before the specified date
            if(todoItem.getDeadLine().isBefore(today)){
                List<TodoItem> filteredByDeadlineBefore = new ArrayList<>();
                filteredByDeadlineBefore.add(todoItem);
                return filteredByDeadlineBefore;
            }
        }
        return null;
    }

    @Override
    public List<TodoItem> findByDeadlineAfter(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date == null) throw new IllegalArgumentException("date is null");
        for (TodoItem todoItem : todoItemsList){
            if(todoItem.getDeadLine().isAfter(today)){
                List<TodoItem> filteredByDeadlineAfter = new ArrayList<>();
                filteredByDeadlineAfter.add(todoItem);
                return filteredByDeadlineAfter;
            }
        }
        return null;

    }

    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("id is empty");
        for (TodoItem todoItem : todoItemsList){
            if(todoItem.getId() == id){
                todoItemsList.remove(todoItem);
            }
        }
    }
}
