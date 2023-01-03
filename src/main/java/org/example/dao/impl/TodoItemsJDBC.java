package org.example.dao.impl;

import org.example.Exception.DBConnectionException;
import org.example.dao.TodoItems;
import org.example.model.MySqlConnection;
import org.example.model.Person;
import org.example.model.TodoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;



public class TodoItemsJDBC implements TodoItems {
    int todo_id = 0;
    String title = null;
    String description = null;
    Date deadline = null;
    boolean done = false;
    int assignee_id = 0;



    @Override
    public TodoItem create(TodoItem todoItem) {
        String query = "insert into todo_item(title,description,deadline,done) values(?,?,?,?)";
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatementTodoItem = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ){
            //set values
            preparedStatementTodoItem.setString(1,todoItem.getTitle());
            preparedStatementTodoItem.setString(2,todoItem.getDescription());
            preparedStatementTodoItem.setDate(3, Date.valueOf(todoItem.getDeadLine()));
            preparedStatementTodoItem.setBoolean(4,todoItem.getDone());
           // preparedStatementTodoItem.setInt(5,todoItem.getAssignee().getId());





            int res =  preparedStatementTodoItem.executeUpdate();
            System.out.println(res);

            try (ResultSet resultSet = preparedStatementTodoItem.getGeneratedKeys()){
                if (resultSet.next()){
                    todo_id = resultSet.getInt(1);
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return findById(todo_id);
    }

    @Override
    public Collection<TodoItem> findAll() {
        String query = "select * from todo_item";
        Collection<TodoItem> todoItemCollection = null;
        PeopleJDBC peopleJDBC = new PeopleJDBC();
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            try (ResultSet resultSet =  preparedStatement.executeQuery();){
                while (resultSet.next()){
                    todoItemCollection = new ArrayList<>();
                       todoItemCollection.add(new TodoItem(
                            resultSet.getInt("todo_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("deadline").toLocalDate(),
                            resultSet.getBoolean("done"),
                               peopleJDBC.findById(resultSet.getInt("assignee_id"))
                    ));
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItemCollection;
    }

    @Override
    public TodoItem findById(int id) {
        String query = "select * from todo_item where todo_id=?";
        TodoItem todoItem = null;
        PeopleJDBC peopleJDBC = new PeopleJDBC();
        try(
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {

            preparedStatement.setInt(1,id);

           try(ResultSet resultSet = preparedStatement.executeQuery();){
               if(resultSet.next()){
                  todoItem = new TodoItem(
                           resultSet.getInt("todo_id"),
                           resultSet.getString("title"),
                           resultSet.getString("description"),
                           resultSet.getDate("deadline").toLocalDate(),
                           resultSet.getBoolean("done"),
                          peopleJDBC.findById(resultSet.getInt("assignee_id"))

                   );
               }
           }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean doneStatus) {
        String query = "select * from todo_item where done=?";
        Collection<TodoItem> todoItemCollection = null;
        try(
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            preparedStatement.setBoolean(1,doneStatus);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    todoItemCollection = new ArrayList<>();
                    todoItemCollection.add(findById(resultSet.getInt("todo_id")));
                }
            }


        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return todoItemCollection;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int assignee) {
        String query = "select * from todo_item where assignee_id=?";
        Collection<TodoItem> todoItemCollection  = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setInt(1,assignee);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    todoItemCollection = new ArrayList<>();
                    todoItemCollection.add(findById(resultSet.getInt("todo_id")));
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItemCollection;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        String query = "select * from todo_item where assignee_id=?";
        PeopleJDBC peopleJDBC = new PeopleJDBC();
        Collection<TodoItem> todoItemCollection = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

                preparedStatement.setInt(1,person.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        todoItemCollection = new ArrayList<>();
                        todoItemCollection.add(new TodoItem(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline").toLocalDate(),
                                resultSet.getBoolean("done"),
                                peopleJDBC.findById(resultSet.getInt("assignee_id"))
                        ));
                    }
                }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItemCollection;
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        String query = "select * from todo_item where assignee_id=?";
        Collection<TodoItem> todoItemCollection = null;
        PeopleJDBC peopleJDBC = new PeopleJDBC();
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setInt(1,0);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    todoItemCollection = new ArrayList<>();
                    todoItemCollection.add(new TodoItem(
                            resultSet.getInt("todo_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("deadline").toLocalDate(),
                            resultSet.getBoolean("done"),
                            peopleJDBC.findById(resultSet.getInt("assignee_id"))

                    ));
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItemCollection;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String query = "update todo_item set title=?, description=?, deadline=?,done=?,assignee_id=?";
        TodoItem todoItemUpdate = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
                    preparedStatement.setString(1,todoItem.getTitle());
                    preparedStatement.setString(2,todoItem.getDescription());
                    preparedStatement.setDate(3,Date.valueOf(todoItem.getDeadLine()));
                    preparedStatement.setBoolean(4,todoItem.getDone());
                    preparedStatement.setInt(5,todoItem.getAssignee().getId());
                    int res = preparedStatement.executeUpdate();
                    System.out.println(res);

            try (ResultSet resultSet = preparedStatement.executeQuery("select * from todo_item")){
                if(resultSet.next()){
                    todoItemUpdate = findById(resultSet.getInt("todo_id"));
                }
            }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoItem;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "delete from todo_item where todo_id=?";
         boolean dele = false;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
                    preparedStatement.setInt(1,id);

                  int res =  preparedStatement.executeUpdate();
                  if (res == 1){
                      dele = true;
                  }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dele;
    }
}
