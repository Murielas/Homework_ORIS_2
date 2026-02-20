package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.service.UserService;

public class Application {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext("ru.itis");

        UserService userService = appContext.getBean(UserService.class);
        userService.saveNewUser("Аня");
        userService.changeName(1L, "Ина");
        userService.getUserByName("Ина");
        userService.deleteUser(1L);
    }
}
