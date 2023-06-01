package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.category.domain.Category;
import hs.kr.dgsw.treamprototype.domain.category.domain.repository.CategoryRepository;
import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import hs.kr.dgsw.treamprototype.domain.post.exception.NotFoundPostException;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public void execute(
            Long id,
            PostRequest request
    ) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> NotFoundPostException.EXCEPTION);

        categoryRepository.deleteCategoryByPost_Id(id);
        categoryRepository.flush();

        post.updatePost(
               request.getTitle(),
               request.getSubTitle(),
               request.getDetailContent()
        );


        List<Category> categories = request.getCategories().stream().map(Category::new).collect(Collectors.toList());

        for (Category c : categories) {
            post.addCategory(c);
        }

    }
}
