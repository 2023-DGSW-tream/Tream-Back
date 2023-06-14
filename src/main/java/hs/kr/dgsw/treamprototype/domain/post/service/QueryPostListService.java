package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.category.domain.repository.CategoryRepository;
import hs.kr.dgsw.treamprototype.domain.category.presentaion.CategoryResponse;
import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response.PostListResponse;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryPostListService {

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PostListResponse execute(int page) {
        Pageable pageable = PageRequest.of(page -1, 2, Sort.Direction.DESC, "crateAt");
        Page<Post> posts = postRepository.findAll(pageable);
        return new PostListResponse(
                posts.stream().map(
                        it -> new PostListResponse.PostResponse(it.getId(), it.getModifyAt(), it.getTitle(), getCategoriesByPostId(it.getId()))
                ).collect(Collectors.toList())
        );

    }


    private List<CategoryResponse> getCategoriesByPostId(Long postId) {
        return categoryRepository.findByPost_Id(postId).stream().map(
                it -> new CategoryResponse(it.getId(), it.getCategory())
        ).collect(Collectors.toList());
    }

}
