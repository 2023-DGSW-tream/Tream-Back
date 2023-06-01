package hs.kr.dgsw.treamprototype.domain.post.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {
    private String title;

    private String subTitle;

    private String detailContent;

    private List<String> categories;
}