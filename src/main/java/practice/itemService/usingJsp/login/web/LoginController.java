package practice.itemService.usingJsp.login.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.itemService.usingJsp.login.dto.BloodType;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @ModelAttribute("bloodTypes")
    public BloodType[] bloodTypes() {
        return BloodType.values();
    }

    @ModelAttribute("sex")
    public Map<String, String> sex() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("m", "Male");
        map.put("f", "Female");
        return map;
    }

    @GetMapping("/")
    public String loginMain() {
        return "login/login";
    }

    @GetMapping("/signUp.cm")
    public String signUpForm() {
        return "login/signUp";
    }

    @PostMapping("/signUp.cm")
    public String signUp(
            @Validated @ModelAttribute("user") SaveUserRequest saveUserRequest
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login/signUp";
        }

        return null;
    }




}

