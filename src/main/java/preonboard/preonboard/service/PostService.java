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
        // 멤버 가져오기
        Member member = getMember();
        // post 저장
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setMember(member);
        postRepository.save(post);

    }

    private Member getMember() {
        // 인증 및 멤버 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authenticated(authentication);
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_THAT_MEMBER));
        return member;
    }

    public Page<Post> getAllPost(Pageable pageable) {
        Page<Post> all = postRepository.findAll(pageable);
        return all;
    }

    public PostResponse getOnePost(Long id) {
        // post 가져옴,없으면 예외처리
        Post post = getPost(id);
        return new PostResponse(post);
    }


    public PostResponse editPost(Long id, String title, String content) {
        // post 가져옴, 없으면 예외처리
        Post post = getPost(id);
        // 멤버 가져오기
        Member member = getMember();
        // 저장
        post.setTitle(title);
        post.setContent(content);
        post.setMember(member);
        postRepository.save(post);
        return new PostResponse(post);
    }
    public void deletePost(Long id) {
        // post 가져옴, 없으면 예외처리
        Post post = getPost(id);
        // 멤버 가져오기
        Member member = getMember();
        if (!post.getMember().equals(member)) {
            throw new BaseException(BaseResponseStatus.FORBIDDEN);
        }
        //삭제
        postRepository.delete(post);
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
