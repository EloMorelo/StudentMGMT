package com.StudentMGMT.entities;

import java.util.UUID;

public class Group {
	
	private UUID id;
	private String name;
	private int year;
	
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
