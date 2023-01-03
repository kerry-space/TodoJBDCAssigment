package org.example.model;

import  java.lang.Object;
import java.time.LocalDate;
import java.util.Objects;

public class AppUser {
    //fields
    private Integer id;
    private String username;
    private String password;
    private AppRole role;

    private boolean active;

    private LocalDate register;

    //Constructor
    public AppUser(String username, String password, AppRole role, boolean active, LocalDate register) {

     setUsername(username);
     setPassword(password);
     setRole(role);
     setActive(active);
     setRegister(register);
    }


    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id == null) throw new RuntimeException("id was null");
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null || username.length() == 0) throw  new IllegalArgumentException("your para is empty");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null || password.length() == 0) throw  new IllegalArgumentException("your para is empty");
        this.password = password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        if(role == null ) role = AppRole.ROLE_APP_USER;
        this.role = role;
    }


    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRegister() {
        return register;
    }

    public void setRegister(LocalDate register) {
        if(register == null) throw new IllegalArgumentException("register is null");
        this.register = register;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username)  && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    public int hashCode(Object obj){

        return this.getPassword().hashCode();

    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", active=" + active +
                ", register=" + register +
                '}';
    }
}
