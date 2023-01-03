package org.example.dao.impl;

import org.example.Exception.DBConnectionException;
import org.example.dao.People;
import org.example.model.MySqlConnection;
import org.example.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
//---------------crud operation----------------------
public class PeopleJDBC implements People {
    int person_id = 0;
    String first_name = null;
    String last_name = null;



    Collection<Person> personCollection = new ArrayList<Person>();

    //---create----
    public Person create(Person person) {
        String query = "insert into person(first_name, last_name) values(?,?)";
        Person person1 = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ){
            //setdata to database
            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());
            int res = preparedStatement.executeUpdate();
            System.out.println(res);

           try( ResultSet resultSet = preparedStatement.getGeneratedKeys();){
               if(resultSet.next()){
                   person_id = resultSet.getInt(1);
               }
           }

            person1 = findById(person_id);

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return person1;
    }

    public Collection<Person> findAll() {
        String query = "select * from person";

        String firstName = null;
        String lastName = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");

                    personCollection.add(new Person(firstName,lastName));
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return personCollection;
    }

    public Person findById(int id) {

        String query = "select * from person where person_id = ?";
        Person person = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    person = new Person(
                            resultSet.getInt("person_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"));

                };
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return person;
    }

    public Collection<Person> findByName(String name) {
        String query = "select * from person where first_name = ?";
        Person person = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            preparedStatement.setString(1,name);

           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if(resultSet.next()){
                   person_id = resultSet.getInt("person_id");
                     personCollection.add(findById(person_id));
               }
           }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return personCollection;
    }

    public Person update(Person person) {
        String query = "update person set first_name = ?, last_name = ? where person_id = ? ";
        Person person1 = null;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            //set values
            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());
            preparedStatement.setInt(3,person.getId());

            int res =  preparedStatement.executeUpdate();
            System.out.println(res);

            if (res < 0) throw new RuntimeException("did not update");

            try(ResultSet resultSet = preparedStatement.executeQuery("select * from person")){
                if (resultSet.next()){
                    person_id = resultSet.getInt(1);
                    person1 = findById(person_id);
                }
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }


        return person1;
    }

    public Boolean deleteById(int id) {
        String query = "delete from person where person_id=?";
        boolean del = false;
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setInt(1,id);

           int res = preparedStatement.executeUpdate();
            if(res == 1){
                del = true;
            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return del;
    }
}
