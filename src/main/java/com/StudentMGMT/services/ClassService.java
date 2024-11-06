package com.StudentMGMT.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.dao.ClassDao;
import com.StudentMGMT.entities.Class;

public class ClassService {
    private ClassDao classDao;

    public ClassService() {
        this.classDao = new ClassDao();
    }

    public List<Class> getAllClasses() throws ClassNotFoundException {
        return classDao.getAllClasses();
    }
    
    public void addClassToSchedule(UUID groupId, String className, String building, String room,UUID teacherId, LocalDate date, LocalTime startTime, LocalTime endTime) throws ClassNotFoundException {
        classDao.addClassToSchedule(groupId, className, building, room, teacherId, date, startTime, endTime);
        
    }
    
    public List<Class> getClassesByUserAndDate(UUID userId, LocalDate date,String role) {
        
        return classDao.getClassesByUserAndDate(userId, date , role);
    }
}
