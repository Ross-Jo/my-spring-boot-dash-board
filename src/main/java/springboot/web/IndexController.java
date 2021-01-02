package springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.config.auth.LoginUser;
import springboot.config.auth.dto.SessionUser;
import springboot.service.posts.PostsService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // CustomOAuth2UserService를 통해 로그인 성공시 세션에 SessionUser를 저장하도록 구성
        if (user != null) model.addAttribute("userName", user.getName());

        return "index";
    }

    @GetMapping("/posts/write")
    public String postsWrite() {
        return "posts-write";
    }

    @GetMapping("/posts/{id}")
    public String singlePosts(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findPostById(id));
        return "single-post";
    }
}
