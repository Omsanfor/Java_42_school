package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import org.postgresql.util.PSQLException;

import javax.sql.DataSource;
import java.sql.SQLException;
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

    public static void main(String[] args) throws SQLException {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        long id;
        System.out.println("Enter a message ID");
        try (Scanner scanner = new Scanner(System.in)) {
            while(!scanner.hasNextLong()) {
                System.out.println("Error, Enter a message ID:");
                scanner.next();
            }
            id = scanner.nextLong();
            Optional<Message> byId = messagesRepository.findById(id);
            if (byId.isPresent()) {
                System.out.println(byId.get());
            }
        }
    }
}
