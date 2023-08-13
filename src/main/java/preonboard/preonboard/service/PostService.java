package preonboard.preonboard.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.domain.Member;
import preonboard.preonboard.domain.Post;
import preonboard.preonboard.dto.PostResponse;
import preonboard.preonboard.dto.base.BaseException;
import preonboard.preonboard.dto.base.BaseResponseStatus;
import preonboard.preonboard.repository.MemberRepository;
import preonboard.preonboard.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    public void writePost(String title, String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authenticated(authentication);
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_THAT_MEMBER));
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setMember(member);
        postRepository.save(post);

    }

    public Page<Post> getAllPost(Pageable pageable) {
        Page<Post> all = postRepository.findAll(pageable);
        return all;
    }

    public PostResponse getOnePost(Long id) {
        Post post = getPost(id);
        return new PostResponse(post);
    }


    public PostResponse editPost(Long id, String title, String content) {
        Post post = getPost(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authenticated(authentication);
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_THAT_MEMBER));
        post.setTitle(title);
        post.setContent(content);
        post.setMember(member);
        postRepository.save(post);
        return new PostResponse(post);
    }

    private Post getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_THAT_ID_POST));
        return post;
    }

    private void authenticated(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BaseException(BaseResponseStatus.AUTHENTICATE_FAILED);
        }
    }

}
