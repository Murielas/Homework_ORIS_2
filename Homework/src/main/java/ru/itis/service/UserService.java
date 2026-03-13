package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.persistence.entity.UserEntity;
import ru.itis.persistence.entity.UserStatus;
import ru.itis.persistence.repository.JdbcUserRepositoryImpl;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JdbcUserRepositoryImpl userRepository;

    public void saveNewUser(String name) {
        if (userRepository.getByName(name) != null) {
            System.out.println("Имя занято");
            return;
        }

        UserEntity user = UserEntity.builder()
                .name(name)
                .status(UserStatus.REGISTERED)
                .build();

        userRepository.save(user);
    }

    public Optional<UserEntity> getUserByName(String name) {
        return userRepository.getByName(name);
    }

    public void changeName(UUID id, String name) {
        Optional<UserEntity> user = userRepository.getById(id);

        if (!userRepository.getByName(name).isEmpty() && !user.isEmpty()) {
            UserEntity userEntity = user.get();
            userEntity.setName(name);
            userRepository.update(userEntity);
        } else {
            System.out.println("Не удалось изменнить имя");
        }
    }

    public void deleteUser(UUID id) {
        if (!userRepository.getById(id).isEmpty()) {
            userRepository.deleteById(id);
        } else {
            System.out.println("Пользователя не существует");
        }
    }
}
