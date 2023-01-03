package org.example.dao.impl;

import org.example.dao.impl.Sequencer.TodoItemTaskIdSequencer;
import org.example.dao.TodoItemTaskDAO;
import org.example.model.TodoItemTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO {

    //field
    List<TodoItemTask> todoItemTasksList;
    private static TodoItemTaskDAOCollection instance;

    //constructor
    private TodoItemTaskDAOCollection(){
        //initialing ones call TodoItemTaskCollection class
        todoItemTasksList = new ArrayList<>();
    }

    //getter and setter

    public static TodoItemTaskDAOCollection getInstance(){
        if(instance == null) instance = new TodoItemTaskDAOCollection();
        return instance;
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("todoItemTask is null");
        todoItemTask.setId(TodoItemTaskIdSequencer.nextId());
        todoItemTasksList.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        if(id == 0) throw new IllegalArgumentException("id is empty");
        for (TodoItemTask todoItemTask : todoItemTasksList){
            if(todoItemTask.getId() == id){
                return todoItemTask;
            }
        }
        return null;
    }

    @Override
    public List<TodoItemTask> findAll() {
        return todoItemTasksList;
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        for (TodoItemTask todoItemTask : todoItemTasksList){
            if(todoItemTask.getAssigned()){
                List<TodoItemTask> filteredByAssignedStatus = new ArrayList<>();
                filteredByAssignedStatus.add(todoItemTask);
                return filteredByAssignedStatus;
            }
        }
        return null;
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        if (personId == 0) throw new IllegalArgumentException("personId is empty");
        for (TodoItemTask todoItemTask: todoItemTasksList){
            List<TodoItemTask> filteredByPersonId = new ArrayList<>();
            filteredByPersonId.add(todoItemTask);
            return filteredByPersonId;
        }
        return null;
    }

    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("id is empty");
        for (TodoItemTask todoItemTask : todoItemTasksList){
            if (todoItemTask.getId() == id){
                todoItemTasksList.remove(todoItemTask);
            }
        }
    }
}
