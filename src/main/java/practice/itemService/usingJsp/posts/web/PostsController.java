package practice.itemService.usingJsp.posts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostsController {

    // 인사말
    @GetMapping("/greeting")
    public String greeting() {
        return "posts/greeting";
    }

}
