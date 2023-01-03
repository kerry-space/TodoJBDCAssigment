package org.example.dao;

import org.example.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TodoItemDAO extends BaseDao<TodoItem> {

    TodoItem findById(int id);

    TodoItem findById(Integer id);
    List<TodoItem> findAllByDoneStatus(boolean done);
    List<TodoItem> findByTitleContains(String title);
    List<TodoItem> findByPerson(int personId);
    List<TodoItem> findByDeadlineBefore(LocalDate date);
    List<TodoItem> findByDeadlineAfter(LocalDate date);

}
