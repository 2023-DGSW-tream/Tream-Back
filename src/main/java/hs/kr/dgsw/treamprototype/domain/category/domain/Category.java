package hs.kr.dgsw.treamprototype.domain.category.domain;

import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Category(String s) {
        this.category = s;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
