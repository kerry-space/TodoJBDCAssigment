package org.example.Exception;

import com.google.protobuf.Message;

public class DBConnectionException extends Throwable {
    //more fields

    public DBConnectionException(String message){
        super(message);
    }
}
