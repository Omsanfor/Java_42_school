package edu.school21.models;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private Boolean authStatus;

    public User() {
    }

    public User(Long id, String login, String password, Boolean authStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authStatus = authStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(boolean authStatus) {
        this.authStatus = authStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return authStatus == user.authStatus && Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, authStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authStatus=" + authStatus +
                '}';
    }
}
