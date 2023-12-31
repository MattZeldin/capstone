package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

   private int id;
   private String username;
   @JsonIgnore
   private String password;
   private String email;
   private String name;
   @JsonIgnore
   private boolean activated;
   private Set<Authority> authorities = new HashSet<>();

   private int days;
   private int minutes;


   public int getDays() {
      return days;
   }

   public void setDays(int days) {
      this.days = days;
   }

   public int getMinutes() {
      return minutes;
   }

   public User(int id, String username, String password, String email, String name, boolean activated, Set<Authority> authorities, int days, int minutes) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.email = email;
      this.name = name;
      this.activated = activated;
      this.authorities = authorities;
      this.days = days;
      this.minutes = minutes;
   }

   public void setMinutes(int minutes) {
      this.minutes = minutes;
   }



   public User() { }

   public User(int id, String username, String password, String authorities, String email, String name) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.email = email;
      this.name = name;
      if (authorities != null) this.setAuthorities(authorities);
      this.activated = true;
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   public String getName(){
      return name;
   }
   public String getEmail(){
      return email;
   }
//
   public void setEmail(String email){
      this.email = email;
   }

   public void setName(String name){
      this.name = name;
   }

   public boolean isActivated() {
      return activated;
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }

   public Set<Authority> getAuthorities() {
      return authorities;
   }

   public void setAuthorities(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for (String role : roles) {
         String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
         this.authorities.add(new Authority(authority));
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              activated == user.activated &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(authorities, user.authorities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, password, activated, authorities, email, name);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", email='" + email + '\'' +
              ", name='" + name + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              ", days=" + days +
              ", minutes=" + minutes +
              '}';
   }
}
