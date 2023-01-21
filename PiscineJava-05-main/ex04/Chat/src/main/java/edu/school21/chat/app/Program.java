package edu.school21.chat.app;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import java.util.List;


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
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(ds);
        List<User> all = usersRepository.findAll(6, 4);
        for (User u : all) {
            System.out.println(u);
        }
    }

}


