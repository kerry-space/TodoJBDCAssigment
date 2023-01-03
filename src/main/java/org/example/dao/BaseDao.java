package org.example.dao;

import org.example.model.AppUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic programming is  a style of computer programming
 * IT is a technique of writting the code without specifying dataType()
 * E: Element
 * T: Type
 * K: Key
 * N: Number
 * V: Value
 * S, U, V ent etc
 */

public interface BaseDao<T>{

    T persist(T model);
    List<T> findAll();
    void remove(Integer id);
}
