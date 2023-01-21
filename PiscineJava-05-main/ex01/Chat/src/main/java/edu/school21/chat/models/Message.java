package edu.school21.chat.models;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private ChatRoom room;
    private String text;
    private Date date;

    public Message(long id, User author, ChatRoom room, String text, Date date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, date);
    }

    @Override
    public String toString() {
        return "Message : {" + "\n" +
                "id=" + id +  ",\n" +
                "author=" + author + ",\n" +
                "room=" + room + ",\n" +
                "text='" + text + '\'' + ",\n" +
                "dateTime=" + new SimpleDateFormat("yy/MM/dd HH:mm").format(date) + "\n" +
                '}';
    }
}
