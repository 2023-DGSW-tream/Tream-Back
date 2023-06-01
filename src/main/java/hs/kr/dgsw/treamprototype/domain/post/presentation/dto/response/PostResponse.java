package hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response;

import hs.kr.dgsw.treamprototype.domain.category.presentaion.CategoryResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponse {

    private Long id;

    private String title;

    private String subTitle;

    private String detailContent;

    private List<CategoryResponse> categories;

    private LocalDateTime createAt;

}
