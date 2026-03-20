package ru.itis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends OrisBaseEntity {
    @Column(name = "user_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus status = UserStatus.REGISTERED;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

    public void addPost(PostEntity post) {
        posts.add(post);
        post.setAuthor(this);
    }
}
