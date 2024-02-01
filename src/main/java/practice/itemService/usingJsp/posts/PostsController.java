package practice.itemService.usingJsp.posts;

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

    // 오시는 길
    @GetMapping("/contact")
    public String contact() {
        return "posts/contact";
    }


    @GetMapping("/schedule")
    public String schedule() {
        return "posts/schedule";
    }
}
