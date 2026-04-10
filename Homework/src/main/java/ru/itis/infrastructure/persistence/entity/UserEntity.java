package ru.itis.infrastructure.persistence.entity;

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
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus status = UserStatus.REGISTERED;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

    private String password;

    public void addPost(PostEntity post) {
        posts.add(post);
        post.setAuthor(this);
    }

    //@ManyToMany
    //@JoinTable(
    //        name = "like",
    //        joinColumns = {
    //                @JoinColumn(name = "account_id")},
    //        inverseJoinColumns = {
    //                @JoinColumn(name = "post_id")
    //        }
    //)
    //private List<PostEntity> likes;

    public enum UserRole {
        USER,
        EDITOR,
        ADMIN
    }
}
