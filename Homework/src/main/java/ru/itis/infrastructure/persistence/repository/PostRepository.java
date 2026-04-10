package ru.itis.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.itis.infrastructure.persistence.entity.PostEntity;
import ru.itis.infrastructure.persistence.entity.UserEntity;

import java.util.UUID;

@Repository
public class PostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public PostEntity savePostForUser(UUID userId, PostEntity post) {
        UserEntity user = entityManager.find(UserEntity.class, userId);

        if (user == null) {
            throw new RuntimeException("Такого пользователя не существует");
        }

        post.setAuthor(user);
        entityManager.persist(post);
        user.getPosts().add(post);
        return post;
    }
}
