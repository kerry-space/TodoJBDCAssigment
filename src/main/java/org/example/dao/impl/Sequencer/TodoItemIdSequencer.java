package org.example.dao.impl.Sequencer;

public class TodoItemIdSequencer {
    //filed
    private static int currentId =  1000;

    //methods
    public static int nextId(){
        ++currentId;
        return currentId;
    }

    public static int getCurrentId(){
        return currentId;
    }
    public static void setCurrentId(int currentId){
        currentId = currentId;

    }
}
