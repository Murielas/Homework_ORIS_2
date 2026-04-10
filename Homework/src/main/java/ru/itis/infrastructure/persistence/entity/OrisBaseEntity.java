package ru.itis.infrastructure.persistence.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class OrisBaseEntity { 
    @Id
    @Column(name= "id", nullable = false, unique = true)
    private UUID id;
    
    @Column(name = "creation_dt", nullable = false)
    LocalDateTime creationDt;

    @Column(name = "last_action_dt", nullable = false)
    LocalDateTime lastActionDt;

    @Column(name = "deleted_dt")
    LocalDateTime deletedDt;

    @PrePersist
    protected void prePersist() {
        if (Objects.isNull(id)) {
            setId(UUID.randomUUID());
        }
        LocalDateTime now = LocalDateTime.now();
        setCreationDt(now);
        setLastActionDt(now);
        setDeletedDt(null);
    }

    @PreUpdate
    protected void preUpdate() {
        setLastActionDt(LocalDateTime.now());
    }

    @PreDestroy
    protected void preDestroy() {
        setDeletedDt(LocalDateTime.now());
    }
}
