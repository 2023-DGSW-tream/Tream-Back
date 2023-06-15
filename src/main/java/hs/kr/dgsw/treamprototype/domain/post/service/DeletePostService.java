package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.post.domain.Post;
import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import hs.kr.dgsw.treamprototype.domain.post.exception.NotFoundPostException;
import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;

    @Transactional
    public void execute(Long id, User user) {
         Post post = postRepository.findById(id)
                 .orElseThrow(() -> NotFoundPostException.EXCEPTION);

         post.matchesUser(user);

         postRepository.deleteById(id);
    }

}
