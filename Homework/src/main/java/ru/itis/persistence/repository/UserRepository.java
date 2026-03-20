package ru.itis.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.itis.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
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
