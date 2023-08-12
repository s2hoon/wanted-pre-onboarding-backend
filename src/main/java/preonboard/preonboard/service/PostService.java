package preonboard.preonboard.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.domain.Post;
import preonboard.preonboard.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;


    public void writePost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);

    }


    public Page<Post> getAllPost(Pageable pageable) {
        Page<Post> all = postRepository.findAll(pageable);
        return all;

    }


}
