package ru.itis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.persistence.entity.UserEntity;
import ru.itis.persistence.entity.UserStatus;
import ru.itis.persistence.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity saveNewUser(String name) {
        UserEntity user = UserEntity.builder()
                .name(name)
                .status(UserStatus.REGISTERED)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void changeName(UUID id, String name) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (!user.isEmpty()) {
            UserEntity userEntity = user.get();
            userEntity.setName(name);
            userRepository.save(userEntity);
        } else {
            System.out.println("Не удалось изменить имя");
        }
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.findById(id).isEmpty()) {
            userRepository.deleteById(id);
        } else {
            System.out.println("Пользователя не существует");
        }
    }
}
