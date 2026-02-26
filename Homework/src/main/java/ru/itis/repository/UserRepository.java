package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.DataManager;
import ru.itis.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    private DataManager dataManager;

    @Autowired
    public UserRepository(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void createUser(String name) throws SQLException {
        String sql = "insert into users (name) values (?)";

        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка создания пользователя");
        }
    }

    public UserEntity findUserByName(String name) throws SQLException {
        String sql = "select * from users where name = ?";

        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserEntity(resultSet.getLong("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка нахождения пользователей");
        }
        return null;
    }

    public void updateName(Long id, String newName) {
        String sql = "update users set name = ? where id = ?";
        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибкa изменения имени");
        }
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "delete from users where id = ?";
        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибкa удаления пользователя");
        }
    }
}
