package practice.itemService.usingJsp.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginMain() {
        return "login/login";
    }

    @GetMapping("/signUp.cm")
    public String join() {
        return "login/signUp";
    }



}

