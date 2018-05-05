package com.studio_projektowe.communicator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "AppUser")
public class AppUser {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true)
    private String email;
    @JsonIgnoreProperties
    private String role;
    private String personalData;
    private Boolean enabled;



    public AppUser() {}


    public AppUser(String username, String login, String password, String email, String role, String personalData, Boolean enabled) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.personalData = personalData;
        this.enabled = enabled;
    }

    public AppUser(Integer id, String username, String login, String password, String email, String role, String personalData, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.personalData = personalData;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
