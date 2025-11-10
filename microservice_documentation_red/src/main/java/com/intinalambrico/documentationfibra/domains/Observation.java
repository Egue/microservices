package com.intinalambrico.documentationfibra.domains;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Observation {

    private String comment;

    private LocalDateTime createdAt;

    private String user;

    public Observation(){
        this.createdAt = LocalDateTime.now();
    }

    public Observation(String comment , String user){
        this.comment = comment;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
