package com.infotech.jersey.model;

import java.io.Serializable;
  
public class User implements Serializable {
  
	private static final long serialVersionUID = -8040774021158552554L;

	private int id;
    private String firstName;
    private String lastName;
  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
