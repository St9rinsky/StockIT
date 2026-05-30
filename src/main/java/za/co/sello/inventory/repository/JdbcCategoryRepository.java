package za.co.sello.inventory.repository;

import za.co.sello.inventory.config.DatabaseConnectionFactory;
import za.co.sello.inventory.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcCategoryRepository {

    private final DatabaseConnectionFactory connectionFactory;

    public JdbcCategoryRepository(DatabaseConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    //Category Object to Table row
    public void save(Category category) {

        String sql = """
                INSERT INTO categories (id, name)
                VALUES (?, ?)
                """;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setObject(1, category.getId());
            statement.setString(2, category.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save category", e);
        }
    }

    //Category table data into a list of category object
    //build categories from table rows
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                UUID id = (UUID) resultSet.getObject("id");
                String name = resultSet.getString("name");

                Category category = new Category(name,id);

                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch categories", e);
        }

        return categories;
    }



    public Optional<Category> findById(UUID id ) {

        String sql = """
                SELECT * FROM categories
                WHERE id = ?
                """;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String name = resultSet.getString("name");
                UUID ident = (UUID) resultSet.getObject("id");

                return Optional.of(new Category(name,ident));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find category", e);
        }
    }

    public Optional<Category> findByName(String categoryName) {

        String sql = """
                SELECT * FROM categories
                WHERE name = ?
                """;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, categoryName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String name = resultSet.getString("name");
                UUID id = (UUID) resultSet.getObject("id");

                return Optional.of(new Category(name,id));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find category", e);
        }
    }

    public void removeByID(UUID id) {
        String sql = """
                DELETE FROM categories
                WHERE id = ?
                """;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setObject(1, id);
            statement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("Failed to remove category", e);}
    }
}