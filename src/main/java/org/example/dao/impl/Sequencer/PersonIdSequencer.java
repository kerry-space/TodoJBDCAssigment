package org.example.dao.impl.Sequencer;

public  class PersonIdSequencer {
    //filed
    private static int currentId = 100;

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
