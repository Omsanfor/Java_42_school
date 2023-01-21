package edu.school21.chat.models;
import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private long id;
    private String name;
    private User owner;
    private List<Message> messages;

    public ChatRoom(long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return id == chatRoom.id && Objects.equals(name, chatRoom.name) && Objects.equals(owner, chatRoom.owner) && Objects.equals(messages, chatRoom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}
