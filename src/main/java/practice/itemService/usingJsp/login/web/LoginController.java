package practice.itemService.usingJsp.login.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.itemService.usingJsp.AES256;
import practice.itemService.usingJsp.AdminPasswordConst;
import practice.itemService.usingJsp.RememberMe;
import practice.itemService.usingJsp.SessionConst;
import practice.itemService.usingJsp.exception.CustomBindingResultException;
import practice.itemService.usingJsp.exception.CustomNoFileException;
import practice.itemService.usingJsp.login.dto.BloodType;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.login.service.LoginServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;

    @ModelAttribute("bloodTypes")
    public BloodType[] bloodTypes() {
        return BloodType.values();
    }

    @ModelAttribute("sex")
    public Map<String, String> sex() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("M", "Male");
        map.put("F", "Female");
        return map;
    }

    // 로그인
    @GetMapping(value = {"/login", "/"})
    public String loginForm(@ModelAttribute("user") User user) throws CustomNoFileException {
        RememberMe.readCredentialsText(user);
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute("user") LoginRequest loginRequest
            , BindingResult bindingResult
            , @RequestParam(name = "redirectURL", defaultValue = "/main") String redirectURL
            , HttpServletRequest request) {

        try {

            User user = loginService.loginProcess(loginRequest, bindingResult);

            // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 user 정보를 담는다.
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_USER, user);

            /**
             * 세션저장소 { UUID : user } 저장.
             * 쿠키저장소 { JSESSIONID : UUID } 저장.
             * 클라이언트는 매 요청시 JSESSIONID 해당 쿠키를 전달한다.
             * 서버에서는 해당 쿠키 정보로 세션저장소를 조회하여 보관한 유저 정보를 사용한다.
             * */

            return "redirect:" + redirectURL;

        } catch (CustomBindingResultException e) {

            return "login/loginForm";

        }



    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    // 회원등록
    @GetMapping("/signUp")
    public String signUpForm() {
        return "login/signUp";
    }

    @Transactional
    @PostMapping("/signUp")
    public String signUp(
            @Validated @ModelAttribute("user") SaveUserRequest saveUserRequest
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        try {

            loginService.signUpProcess(saveUserRequest, bindingResult);

            // 회원 등록 완료 alert
            redirectAttributes.addAttribute("completedWithSigningUp", true);

            return "redirect:/";

        } catch (CustomBindingResultException e) {

            return "login/signUp";

        }

    }



}

