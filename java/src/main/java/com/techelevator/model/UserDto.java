package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDto {

    private int id;
    private String username;
    @JsonIgnore
//    private String password;
    private String email;
    private String name;



    public UserDto() { }

    public UserDto(int id, String username,String email, String name) {
        this.id = id;
        this.username = username;
//        this.password = password;
        this.email = email;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email=" + email +
                ", name=" + name +
                '}';
    }
}
