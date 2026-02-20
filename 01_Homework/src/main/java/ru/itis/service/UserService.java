package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.entity.UserEntity;
import ru.itis.repository.UserRepository;

import java.sql.SQLException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(String name) {
        try {
            if (userRepository.findUserByName(name) != null) {
                System.out.println("Имя занято");
                return;
            } else {
                userRepository.createUser(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания пользователя", e);
        }
    }

    public UserEntity getUserByName(String name) {
        try {
            UserEntity user = userRepository.findUserByName(name);
            if (user != null) {
                return user;
            } else {
                System.out.println("Такого пользователя не существует");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка нахождеия пользователя", e);
        }
    }

    public void changeName(Long id, String name) {
        try {
            if (userRepository.findUserByName(name) == null) {
                userRepository.updateName(id, name);
                return;
            } else {
                System.out.println("Имя занято");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения имени пользователя", e);
        }
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления пользователя", e);
        }
    }
}
