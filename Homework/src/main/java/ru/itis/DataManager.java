package ru.itis;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DataManager {
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: " + e);
        }

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring",
                "postgres", "2006");
    }
}
