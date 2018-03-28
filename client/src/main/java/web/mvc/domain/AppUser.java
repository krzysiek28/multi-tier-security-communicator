package web.mvc.domain;

public class AppUser {
    private Integer id;
    private String username;
    private String login;
    private String password;
    private String email;
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
