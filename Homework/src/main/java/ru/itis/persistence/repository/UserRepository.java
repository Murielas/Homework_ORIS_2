package ru.itis.persistence.repository;


import ru.itis.persistence.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

}
