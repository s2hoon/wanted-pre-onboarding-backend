package preonboard.preonboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import preonboard.preonboard.domain.Post;

@Data
@AllArgsConstructor
public class PostResponse {
    private String title;
    private String content;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
