package ru.itis.entity;

import org.springframework.stereotype.Component;

@Component
public class UserEntity {
    private Long id;
    private String name;

    public UserEntity() { }

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
