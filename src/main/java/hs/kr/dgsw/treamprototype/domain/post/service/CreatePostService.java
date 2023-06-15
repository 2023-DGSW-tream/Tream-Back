package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.category.domain.Category;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.request.PostRequest;
import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    public void execute(
            PostRequest request,
            final User user
    ) {
        Post post = Post.builder()
                        .title(request.getTitle())
                        .subTitle(request.getSubTitle())
                        .detailContent(request.getDetailContent())
                        .categories(new HashSet<>())
                        .build();

        List<Category> categories = request.getCategories().stream().map(Category::new).collect(Collectors.toList());

        for (Category c : categories) {
            post.addCategory(c);
        }

        final Post savedPost = postRepository.save(post);

        user.addPost(savedPost);
        userRepository.save(user);
    }
}
