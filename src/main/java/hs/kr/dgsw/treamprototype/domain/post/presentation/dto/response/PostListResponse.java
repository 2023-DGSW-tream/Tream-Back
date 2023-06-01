package hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response;

import hs.kr.dgsw.treamprototype.domain.category.presentaion.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private List<PostResponse> list;

    @Getter
    @AllArgsConstructor
    public static class PostResponse {
        private Long id;
        private LocalDateTime crateAt;
        private String title;
        private List<CategoryResponse> categories;
    }

}