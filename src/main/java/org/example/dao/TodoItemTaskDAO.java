package org.example.dao;

import org.example.model.TodoItemTask;

import java.util.Collection;

public interface TodoItemTaskDAO extends BaseDao<TodoItemTask> {

    TodoItemTask findById(int id);

    Collection<TodoItemTask> findByAssignedStatus(boolean status);
    Collection<TodoItemTask> findByPersonId(int personId);

}
