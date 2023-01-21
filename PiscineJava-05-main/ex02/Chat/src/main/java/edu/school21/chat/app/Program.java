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
        User creator = new User(1l, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        ChatRoom room = new ChatRoom(1l, "room1", creator, new ArrayList());
        Message message = new Message(1l, author, room, "Hello!", new Date(new java.util.Date().getTime()));
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        messagesRepository.save(message);
        System.out.println(message);
    }
}


