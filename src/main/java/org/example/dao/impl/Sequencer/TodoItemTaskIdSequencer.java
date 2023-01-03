package org.example.dao.impl.Sequencer;

public class TodoItemTaskIdSequencer {
    //filed
    private static int currentId = 10;

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
