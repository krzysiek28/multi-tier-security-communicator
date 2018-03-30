package com.studio_projektowe.communicator.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Post {
    @Id
    @GeneratedValue
    Integer id;
/*    @ManyToOne
    Conversation conversation;
    @OneToOne
    AppUser postOwner;*/
    Time time;
    String text;
    Date date;

    public Post() {
    }

    public Post(/*Conversation conversation, AppUser postOwner,*/ Time time, String text, Date date) {
/*        this.conversation = conversation;
        this.postOwner = postOwner;*/
        this.time = time;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

/*    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public AppUser getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(AppUser postOwner) {
        this.postOwner = postOwner;
    }*/

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
