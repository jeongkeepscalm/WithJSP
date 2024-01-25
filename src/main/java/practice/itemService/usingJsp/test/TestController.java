package practice.itemService.usingJsp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test.cm")
    public String test() {
        return "test";
    }
}

