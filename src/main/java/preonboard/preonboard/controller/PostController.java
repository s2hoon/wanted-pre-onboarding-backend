package preonboard.preonboard.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import preonboard.preonboard.domain.Post;
import preonboard.preonboard.dto.EditPostRequest;
import preonboard.preonboard.dto.PostResponse;
import preonboard.preonboard.dto.WritePostRequest;
import preonboard.preonboard.dto.base.BaseResponse;
import preonboard.preonboard.dto.base.BaseResponseStatus;
import preonboard.preonboard.service.PostService;

@RestController
@RequestMapping("/app/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;

    @PostMapping("/write-post")
    public BaseResponse<String> writePost(@RequestBody WritePostRequest writePostRequest) {
        try {
            String title = writePostRequest.getTitle();
            String content = writePostRequest.getContent();
            postService.writePost(title, content);
            return new BaseResponse<String>(BaseResponseStatus.SUCCESS, "글 작성 성공");
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.WRITE_POST_FAILED);
        }
    }

    @GetMapping("/getAll")
    public BaseResponse<Page<PostResponse>> getAllPost(@PageableDefault(size = 10) Pageable pageable) {
        Page<Post> page = postService.getAllPost(pageable);
        Page<PostResponse> pageDto = page.map(PostResponse::new);
        return new BaseResponse<Page<PostResponse>>(BaseResponseStatus.SUCCESS, pageDto);
    }

    @GetMapping("/getOne")
    public BaseResponse<PostResponse> getOnePost(@RequestParam Long id) {
        PostResponse postResponse = postService.getOnePost(id);
        return new BaseResponse<PostResponse>(BaseResponseStatus.SUCCESS, postResponse);
    }

    @PutMapping("/editPost")
    public BaseResponse<PostResponse> editPost(@RequestBody EditPostRequest editPostRequest)  {
        Long id = editPostRequest.getId();
        String title = editPostRequest.getTitle();
        String content = editPostRequest.getContent();
        PostResponse postResponse = postService.editPost(id, title, content);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, postResponse);

    }
}
