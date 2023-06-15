package hs.kr.dgsw.treamprototype.domain.post.domain;

import hs.kr.dgsw.treamprototype.domain.category.domain.Category;
import hs.kr.dgsw.treamprototype.domain.post.exception.NotMatchesUserException;
import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subTitle;

    private String detailContent;

    @Setter
    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories;

    public void matchesUser(User user) {
        if (!this.user.getId().equals(user.getId())) {
            throw NotMatchesUserException.EXCEPTION;
        }
    }

    public void addCategory(Category category) {
        category.setPost(this);
        categories.add(category);
    }

    public void updatePost(String title, String subTitle, String detailContent) {
        this.title = title;
        this.subTitle = subTitle;
        this.detailContent = detailContent;
    }
}
