package ru.itis.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.resilience.annotation.RetryAnnotationBeanPostProcessor;
import org.springframework.stereotype.Repository;
import ru.itis.infrastructure.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Transactional
    public Optional<UserEntity> findById(UUID id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<UserEntity> findByUsername(String username) {
        try {
            String sql = "select user from UserEntity user where user.username = :username";
            TypedQuery<UserEntity> query = entityManager.createQuery(sql, UserEntity.class);
            query.setParameter("username", username);
            UserEntity user = query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public List<UserEntity> findAll() {
        String sql = "select user from UserEntity user";
        TypedQuery<UserEntity> query = entityManager.createQuery(sql, UserEntity.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(UUID id) {
        UserEntity user = entityManager.find(UserEntity.class, id);

        if (user != null) {
            entityManager.remove(user);
        }
    }
}
