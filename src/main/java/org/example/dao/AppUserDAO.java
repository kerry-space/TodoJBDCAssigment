package org.example.dao;

import org.example.model.AppUser;

import java.util.ArrayList;

public interface AppUserDAO extends BaseDao<AppUser> {



    AppUser findByUsername(String username);


}
