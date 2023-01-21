package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<ChatRoom> createdRooms;
    private List<ChatRoom> activeChatRoom;

    public User() {
    }

    public User(long id, String login, String password, List<ChatRoom> createdRooms, List<ChatRoom> activeChatRoom) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.activeChatRoom = activeChatRoom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRooms(List<ChatRoom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public void setActiveChatRoom(List<ChatRoom> activeChatRoom) {
        this.activeChatRoom = activeChatRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(activeChatRoom, user.activeChatRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, activeChatRoom);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", activeChatroom=" + activeChatRoom +
                '}';
    }
}
