package ru.itis.persistence.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserEntity {
    private UUID id;
    private String name;
    private UserStatus status;
}
