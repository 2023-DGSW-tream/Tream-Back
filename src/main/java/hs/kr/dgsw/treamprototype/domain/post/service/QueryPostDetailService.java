package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.category.domain.Category;
import hs.kr.dgsw.treamprototype.domain.category.domain.repository.CategoryRepository;
import hs.kr.dgsw.treamprototype.domain.category.presentaion.CategoryResponse;
import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import hs.kr.dgsw.treamprototype.domain.post.exception.NotFoundPostException;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryPostDetailService {

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PostResponse execute(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> NotFoundPostException.EXCEPTION);

        List<Category> categories = categoryRepository.findByPost_Id(id);

        List<CategoryResponse> categoryResponses = categories.stream().map(it -> new CategoryResponse(it.getId(), it.getCategory())).collect(Collectors.toList());
        return new PostResponse(
                post.getId(), post.getTitle(), post.getSubTitle(), post.getDetailContent(), categoryResponses, post.getModifyAt()
        );
    }


}
