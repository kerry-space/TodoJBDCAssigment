package org.example.dao;


import org.example.model.Person;
import org.example.model.TodoItem;

import java.util.Collection;

public interface TodoItems {
    TodoItem create(TodoItem todoItem);
    Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean doneStatus);
    Collection<TodoItem> findByAssignee(int assignee);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem todoItem);
    boolean deleteById(int id);

}
