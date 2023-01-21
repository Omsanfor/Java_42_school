package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

    private Connection connection;

    public ProductsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() {
        final String SQL = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Product product = null;
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM products WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE products SET name=?, price=? WHERE id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        PreparedStatement preparedStatement =
                null;
        try {

            preparedStatement = connection.prepareStatement("INSERT INTO products (name, price) VALUES (?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT id FROM products WHERE name=? and price=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM products WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
