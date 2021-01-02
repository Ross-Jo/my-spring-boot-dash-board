package springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.service.posts.PostsService;
import springboot.web.dto.PostsSaveRequestDto;
import springboot.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final PostsService postService;

    @PostMapping("/save")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        return postService.save(postsSaveRequestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return postService.delete(id); // return void 시 parse error 발생.
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postService.update(id, postsUpdateRequestDto);
    }

}
