package org.example.model;


import java.lang.Object;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {



    //Fields
    private Integer id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;


    //Constructor

    public TodoItem(String title, String taskDescription, LocalDate deadLine) {
        setTitle(title);
       setDescription(taskDescription);
        setDeadLine(deadLine);
        setDone(true);
    }


    public  TodoItem(String title, String description, LocalDate deadLine, Person assignee){
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(true);
        setAssignee(assignee);
    }



    public TodoItem(Integer id, String title, String description, LocalDate deadLine) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = true;
    }


    public TodoItem(Integer id, String title, String description, LocalDate deadLine,boolean done, Person assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
    }

    //Getter & Setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null) throw new IllegalArgumentException("title para was null");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String taskDescription) {
        if(taskDescription == null) throw new IllegalArgumentException("taskDescription is null");
        this.description = taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if(deadLine == null)  throw new  IllegalArgumentException("deadLine para was null");
            this.deadLine = deadLine;
            this.done =true;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person creator) {
        if(creator == null) throw new IllegalArgumentException("creator is null");
        this.assignee = creator;
    }




    //methods
    public boolean isOverdue(){
        //should return true if current data has passed deadLine
        LocalDate currentTime = LocalDate.of(2022,9,12);

        if(currentTime.isAfter(deadLine)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadLine, done);
    }
}
