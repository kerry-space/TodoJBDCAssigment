package org.example.model;

import org.example.Exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnection {
    private  static final  String JDBC_USER = "root";
    private  static  final  String JDBC_PASS = "Sandralove2021!";
    private  static  final  String JDBC_URL = "jdbc:mysql://localhost:3306/todoit";

    public static Connection getConnection() throws DBConnectionException {
        try {
            return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
        }catch (SQLException e){
            throw new DBConnectionException("Database connection failed!");

        }
    }
}
