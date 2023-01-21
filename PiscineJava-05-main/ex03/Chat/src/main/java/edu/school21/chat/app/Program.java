package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import org.postgresql.util.PSQLException;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    private static HikariDataSource ds;
    static {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5434/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.addDataSourceProperty( "cachePrepStmts" , "true" );
        ds.addDataSourceProperty(  "prepStmtCacheSize" , "250" );
        ds.addDataSourceProperty(  "prepStmtCacheSqlLimit" , "2048" );
    }


    public static void main(String[] args) {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        User user = new User(50l, "login", "password", null, null);
        Optional<Message> byId = messagesRepository.findById(7l);
        if (byId.isPresent()) {
            Message message = byId.get();
            message.setText("Bye2");
            message.setAuthor(user);
            message.setDate(new Date(new java.util.Date().getTime()));
            messagesRepository.update(message);
        }

    }
}


