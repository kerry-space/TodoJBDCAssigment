package org.example.model;

import java.util.Objects;

public class TodoItemTask {

    //fields
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    //Constructor

    public TodoItemTask(TodoItem todoItem, Person assignee){
        setTodoItem(todoItem);
        setAssignee(assignee);
    }


    //Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAssigned() {
        return assigned;
    }

    public void setAssigned() {

        this.assigned = assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("todoItem para was null");
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if(assignee == null) throw new IllegalArgumentException("assignee para was null");
        this.assignee = assignee;
        this.assigned = true;
    }

    //Methods


    @Override
    public String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", todoItem=" + todoItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
    }
}
