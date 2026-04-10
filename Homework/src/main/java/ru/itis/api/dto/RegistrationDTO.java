package ru.itis.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrationDTO {
    @NotBlank(message = "Заполните поле имя пользователя")
    private String username;

    @NotBlank(message = "Заполните поле пароль")
    @Size(min = 6, max = 12, message = "Пароль должен содержать не меньше 6 символов и не больше 12 символов")
    private String password;
}
