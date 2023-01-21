package edu.school21.chat.models;
import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

    public ChatRoom(Long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return Objects.equals(id, chatRoom.id) && Objects.equals(name, chatRoom.name) && Objects.equals(owner, chatRoom.owner) && Objects.equals(messages, chatRoom.messages);
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
