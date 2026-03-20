package ru.itis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.persistence.entity.PostEntity;
import ru.itis.persistence.repository.PostRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostEntity saveNewPost(UUID userId, String title, String content) {
        PostEntity post = PostEntity.builder()
                .title(title)
                .content(content)
                .build();

        return postRepository.savePostForUser(userId, post);
    }
}
