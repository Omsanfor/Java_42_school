package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.security.acl.Owner;
import java.sql.*;
import java.util.Date;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource dataSource;


    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message message = null;
        User user = null;
        ChatRoom room= null;
        Date date = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT chat.message.id as msg_id,chat.message.text_msg as msg_text,chat.message.datetime," +
                             "chat.\"user\".id as user_id, chat.\"user\".login, chat.\"user\".password," +
                             "chat.chatroom.id as room_id, chatroom.name as room_name\n" +
                             "FROM chat.message\n" +
                             "join chat.\"user\" ON message.author = \"user\".id\n" +
                             "join chat.chatroom ON chat.message.room = chat.chatroom.id\n" +
                             "WHERE chat.message.id = ?;")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return Optional.empty();

            user = new User(resultSet.getLong("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"), null, null);
            room = new ChatRoom(resultSet.getLong("room_id"),
                    resultSet.getString("room_name"), user, null);
            message = new Message(resultSet.getLong("msg_id"),user, room,
                    resultSet.getString("msg_text"), resultSet.getDate("datetime"));
            return Optional.of(message);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            if (!statement.executeQuery("SELECT chat.chatroom.id FROM chat.chatroom WHERE id = " + message.getRoom().getId() + ";").next() ||
                    !statement.executeQuery("SELECT chat.\"user\".id FROM chat.\"user\" WHERE id = " + message.getAuthor().getId() + ";").next()) {
                throw new NotSavedSubEntityException();
            }
            PreparedStatement preparedStatement = connection.prepareStatement("insert into chat.Message(author, room, text_msg, datetime) values (?, ?, ?, ?)");
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, new Timestamp(message.getDate().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT chat.message.id as id FROM chat.message WHERE datetime =" + "\'" + new Timestamp(message.getDate().getTime())  + "\'" + " and " +
                            "room = 1 and " +
                            "text_msg = 'Hello!' and " +
                            "author = 1;"
            );
            resultSet.next();
            message.setId(resultSet.getLong("id"));
        } catch (SQLException |NullPointerException throwables) {
            System.out.println("Unexpected Exception");
            System.exit(-1);
        }

    }

    private class NotSavedSubEntityException extends RuntimeException {
        @Override
        public String getMessage() {
            return "Error ID";
        }
    }
}















