package org.example.dao.impl;

import org.example.dao.AppUserDAO;
import org.example.model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOCollection implements AppUserDAO {
   //Field
    List<AppUser> appUserList;

    private static AppUserDAOCollection instance;

    //Constructor which is singleTonPattern which private constructor, you instantiate ones with field instance inside method getInstance
    private AppUserDAOCollection(){
        //initialing ones call AppUserDaoCollection class
        appUserList = new ArrayList<>();
    }

    //Override methods implements interface

    public static AppUserDAOCollection getInstance(){
        if(instance == null) instance = new AppUserDAOCollection();
        return instance;
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if(appUser == null) throw new IllegalArgumentException("appUser is null");
        appUserList.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        if(username == null) throw new IllegalArgumentException("username is null");
        for (AppUser appUser: appUserList){
            if(appUser.getUsername().equalsIgnoreCase(username)){
                return appUser;
            }
        }
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        //convert our list to arrayList and return arrayList
        ArrayList<AppUser> arraylist = new ArrayList<>(appUserList);
        return arraylist;
    }


    @Override
    public void remove(Integer id) {
        if(id == null) throw new IllegalArgumentException("id is null");
        for(AppUser appUser : appUserList){
            if(appUser.getId() == id){
                appUserList.remove(appUser);
            }
        }
    }
}
