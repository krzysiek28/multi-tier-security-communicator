package com.studio_projektowe.communicator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Column(unique=true, name="name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name ="userId")
    private String userId; //conversation owner's id


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Usr_Con",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id")}
    )
    @JsonIgnore
    Set<AppUser> appUsers = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Post_con",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id")}
    )
    @JsonIgnore
    Set<Post> posts = new HashSet<>();


    public Conversation() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<AppUser> getUsers() {
        return appUsers;
    }

    public void setUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
