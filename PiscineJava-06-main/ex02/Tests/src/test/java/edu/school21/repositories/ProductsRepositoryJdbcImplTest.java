package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.meta.When;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class ProductsRepositoryJdbcImplTest {

    private Connection connection;

    @BeforeEach
    public void init() throws SQLException {
        connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build().getConnection();
    }

    @Test
    public void testSave() throws SQLException {
        final Product EXPECTED_SAVE_PRODUCT = new Product(null, "Flour", 777);
        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(connection);
        productsRepository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertNotNull(EXPECTED_SAVE_PRODUCT.getId());
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepository.findById(EXPECTED_SAVE_PRODUCT.getId()).orElse(null));
    }

    @ParameterizedTest(name = "{index} => isUpdate({0})")
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testUpdate(Long id) throws SQLException {
        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(connection);
        final Product EXPECTED_UPDATE_PRODUCT = productsRepository.findById(id).orElse(null);
        EXPECTED_UPDATE_PRODUCT.setPrice(999);
        productsRepository.update(EXPECTED_UPDATE_PRODUCT);
        Product RESULT_UPDATE_PRODUCT = productsRepository.findById(id).orElse(null);
        Assertions.assertNotNull(RESULT_UPDATE_PRODUCT);
        Assertions.assertEquals(RESULT_UPDATE_PRODUCT, EXPECTED_UPDATE_PRODUCT);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testDelete(long id) {
        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(connection);
        productsRepository.delete(id);
        Assertions.assertThrows(SQLException.class, () -> productsRepository.findById(id));
    }

    @Test
    public void testFindAll() throws Exception {
        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(connection);
        long EXPECTED_SIZE = 5;
        final List<Product> EXPECTED_FINDAll_PRODUCT  = Arrays.asList(
                new Product(0l, "Bread", 50),
                new Product(1l, "Milk", 90),
                new Product(2l, "Eggs", 120),
                new Product(3l, "Juice", 180),
                new Product(4l, "Meat", 390)
        );
        Assertions.assertEquals(EXPECTED_SIZE, productsRepository.findAll().size());
        List<Product> RESULT_FINDAll_PRODUCT = productsRepository.findAll();
        Assertions.assertEquals(RESULT_FINDAll_PRODUCT, EXPECTED_FINDAll_PRODUCT);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testFindByIdTrue(long id) throws SQLException {
        ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(connection);
        Assertions.assertTrue(productsRepository.findById(id).isPresent());
    }

    @ParameterizedTest
    @ValueSource(longs = {99, 99, 15874, 41})
    public void testFindByIdFalse(long id) throws SQLException {
        ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(connection);
        Assertions.assertThrows(SQLException.class, () -> productsRepository.findById(id));
    }
}
