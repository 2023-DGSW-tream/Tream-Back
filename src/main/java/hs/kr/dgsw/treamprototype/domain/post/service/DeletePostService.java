package hs.kr.dgsw.treamprototype.domain.post.service;

import hs.kr.dgsw.treamprototype.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;

    @Transactional
    public void execute(Long id) {
        postRepository.deleteById(id);
    }

}
