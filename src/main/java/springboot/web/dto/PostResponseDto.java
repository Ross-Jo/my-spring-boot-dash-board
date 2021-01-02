package springboot.web.dto;

import lombok.Getter;
import springboot.domain.posts.Posts;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;

    public PostResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }
}
