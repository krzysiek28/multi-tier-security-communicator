package com.studio_projektowe.communicator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    Integer id;
    @OneToMany
    AppUser appUser;
    @OneToOne
    AppUser owner;
    @JsonIgnore
    String password;
    @OneToMany
    Post post;
    //groups


    public Conversation() {
    }

    public Conversation(AppUser appUser, AppUser owner, String password, Post post) {
        this.appUser = appUser;
        this.owner = owner;
        this.password = password;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
