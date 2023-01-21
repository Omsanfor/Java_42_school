package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> activeChatroom;

    public User(long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> activeChatroom) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.activeChatroom = activeChatroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(activeChatroom, user.activeChatroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, activeChatroom);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", activeChatroom=" + activeChatroom +
                '}';
    }
}
