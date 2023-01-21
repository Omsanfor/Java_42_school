package edu.school21.repositories;


import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

public class EmbeddedDataSourceTest {
    @Test
    @BeforeEach
    public void init() throws SQLException {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
        assertNotNull(db);
    }
}
