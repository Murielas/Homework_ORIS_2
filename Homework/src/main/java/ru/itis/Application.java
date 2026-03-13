package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.service.UserService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(Application.class, args);

        UserService userService = appContext.getBean(UserService.class);
        userService.saveNewUser("Аня");
        userService.changeName(userService.getUserByName("Аня").get().getId(), "Ина");
        userService.getUserByName("Ина");
        userService.deleteUser(userService.getUserByName("Ина").get().getId());
    }
}
