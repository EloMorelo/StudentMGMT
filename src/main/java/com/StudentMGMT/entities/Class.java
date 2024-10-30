package com.StudentMGMT.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Class {
    private UUID classId;
    private String name;         
    private String building;     
    private String room;         
    private LocalDate date;    
    private LocalTime startTime; 
    private LocalTime endTime;
    private UUID groupId;        
    private UUID teacherId;      


    public UUID getClassId() {
        return classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
    	return endTime;
    }
    
    public void setEndTime(LocalTime endTime) {
    	this.endTime = endTime;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

}
