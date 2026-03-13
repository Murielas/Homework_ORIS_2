package ru.itis.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itis.persistence.entity.UserEntity;
import ru.itis.persistence.entity.UserStatus;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate namedParameter;

    @Override
    public void save(UserEntity user) {
        String sql = "insert into users (name, status) values (:name, :status::status_type)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("status", user.getStatus().name());
        namedParameter.update(sql, parameterSource);
    }

    @Override
    public Optional<UserEntity> getById(UUID uuid) {
        String sql = "select * from users where id = :id";
        Map<String, Object> parameter = Collections.singletonMap("id", uuid);
        try {
            UserEntity entity = namedParameter.queryForObject(sql, parameter, userMapper);
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> getByName(String name) {
        String sql = "select * from users where name = :name";
        Map<String, Object> parameter = Collections.singletonMap("name", name);
        try {
            UserEntity entity = namedParameter.queryForObject(sql, parameter, userMapper);
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "select * from users";
        return namedParameter.query(sql, userMapper);
    }

    @Override
    public void update(UserEntity user) {
        String sql = "update users set name = :name, status = :status::status_type where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("status", user.getStatus().name())
                .addValue("id", user.getId());
        namedParameter.update(sql, parameterSource);
    }

    @Override
    public boolean deleteById(UUID uuid) {
        String sql = "delete from users where id = :id";
        Map<String, Object> parameter = Collections.singletonMap("id", uuid);
        int deletedRow = namedParameter.update(sql, parameter);
        return deletedRow > 0;
    }

    @Override
    public void deleteAll() {
        String sql = "delete from users";
        namedParameter.update(sql, Collections.emptyMap());
    }

    private final RowMapper<UserEntity> userMapper = (rs, rowNumber) -> {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.fromString(rs.getString("id")));
        entity.setName(rs.getString("name"));
        entity.setStatus(UserStatus.valueOf(rs.getString("status")));
        return entity;
    };
}
