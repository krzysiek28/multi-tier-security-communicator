package web.mvc.domain;

import java.util.HashSet;
import java.util.Set;

public class Conversation {

    private Integer id;
    private String name;
    private String password;
    private String userId; //conversation owner's id
    Set<AppUser> appUsers = new HashSet<>();
    Set<Post> posts = new HashSet<>();

    public Conversation() {
    }

    public Conversation(Integer id, String name, String password, String userId, Set<AppUser> appUsers, Set<Post> posts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.appUsers = appUsers;
        this.posts = posts;
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

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
