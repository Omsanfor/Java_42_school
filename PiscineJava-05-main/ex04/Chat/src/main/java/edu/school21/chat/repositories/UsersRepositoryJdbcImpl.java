package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        int OFFSET = page * size == 0 ? 0 : (page * size) - 1;
        int LIMIT = size;
        if (OFFSET == 0)
            LIMIT = size - 1;
        if (size <= 0 || page < 0 )
            return null;
        List<User> list = new ArrayList<>();

        final String SQL = "SELECT * FROM chat.user LIMIT " + LIMIT +  " OFFSET " + OFFSET + ";";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(new User(resultSet.getLong("id"), resultSet.getString("login"), resultSet.getString("password"), new ArrayList<ChatRoom>(), new ArrayList<ChatRoom>()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
