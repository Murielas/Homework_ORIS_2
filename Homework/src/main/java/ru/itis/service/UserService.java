package ru.itis.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.api.dto.RegistrationDTO;
import ru.itis.infrastructure.persistence.entity.UserEntity;
import ru.itis.infrastructure.persistence.entity.UserStatus;
import ru.itis.infrastructure.persistence.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity saveNewUser(RegistrationDTO regDTO) {
        if (userRepository.findByUsername(regDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Такой пользователь уже существует");
        }

        UserEntity user = UserEntity.builder()
                .username(regDTO.getUsername())
                .password(passwordEncoder.encode(regDTO.getPassword()))
                .role(UserEntity.UserRole.USER)
                .status(UserStatus.REGISTERED)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void changeName(UUID id, String name) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (!user.isEmpty()) {
            UserEntity userEntity = user.get();
            userEntity.setUsername(name);
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
