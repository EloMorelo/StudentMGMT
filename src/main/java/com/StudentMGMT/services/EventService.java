package com.StudentMGMT.services;

import java.util.List;
import java.util.UUID;

import com.StudentMGMT.entities.Event;
import com.StudentMGMT.dao.EventDao;

public class EventService {
	private EventDao eventDao = new EventDao();
	
	public List<Event> getUpcomingEventsByUser(UUID userId) {
        try {
            return eventDao.getAllEvents();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
