package com.StudentMGMT.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String title;
    private String description;
    private Date date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
