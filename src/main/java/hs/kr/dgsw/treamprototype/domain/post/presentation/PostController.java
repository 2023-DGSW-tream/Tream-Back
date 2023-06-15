package hs.kr.dgsw.treamprototype.domain.post.presentation;

import feign.Request;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.request.PostRequest;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response.PostListResponse;
import hs.kr.dgsw.treamprototype.domain.post.presentation.dto.response.PostResponse;
import hs.kr.dgsw.treamprototype.domain.post.service.*;
import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.global.annotation.AuthGuard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostService createPostService;

    private final DeletePostService deletePostService;

    private final UpdatePostService updatePostService;

    private final QueryPostDetailService queryPostDetailService;

    private final QueryPostListService queryPostListService;

    @AuthGuard
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @RequestBody PostRequest request,
            @RequestAttribute User user
    ) {
            createPostService.execute(request, user);
    }

    @GetMapping("/list/{page}")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse getPostList(
            @PathVariable("page") int page
    ) {
        return queryPostListService.execute(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(
            @PathVariable("id") Long id
    ) {
        return queryPostDetailService.execute(id);
    }

    @AuthGuard
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostById(
            @PathVariable("id") Long id,
            @RequestAttribute User user
    ) {
        deletePostService.execute(id, user);
    }

    @AuthGuard
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePostById(
            @RequestBody PostRequest postRequest,
            @PathVariable("id") Long id,
            @RequestAttribute User user
    ) {
        updatePostService.execute(id, postRequest, user);
    }
}
