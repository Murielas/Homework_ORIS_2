package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.persistence.entity.UserEntity;
import ru.itis.service.PostService;
import ru.itis.service.UserService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(Application.class, args);

        UserService userService = appContext.getBean(UserService.class);
        PostService postService = appContext.getBean(PostService.class);
        UserEntity user1 = userService.saveNewUser("Аня");
        userService.changeName(user1.getId(), "Ина");
        postService.saveNewPost(user1.getId(), "Приветствие", "Это мой первый пост");
    }
}
