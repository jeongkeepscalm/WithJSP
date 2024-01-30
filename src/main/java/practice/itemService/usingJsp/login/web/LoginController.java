package practice.itemService.usingJsp.login.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.itemService.usingJsp.SessionConst;
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
        map.put("m", "Male");
        map.put("f", "Female");
        return map;
    }

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute("user") LoginRequest loginRequest
            , BindingResult bindingResult
            , @RequestParam(defaultValue = "/main") String redirectURL
            , HttpServletRequest request) {

        String redirectURL1 = request.getParameter("redirectURL");

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        // 아이디 비밀번호 검증로직
        User user = loginService.checkIfUser(loginRequest);
        if (user == null) {
            bindingResult.addError(new FieldError("user"
                    , "password"
                    , "* 아이디 또는 비밀번호가 맞지 않습니다."));
            return "login/loginForm";
        }

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

    }

    // 로그아웃
    @PostMapping("/logOut")
    public String logOut(HttpServletRequest httpServletRequest) {
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

    @PostMapping("/signUp")
    public String signUp(
            @Validated @ModelAttribute("user") SaveUserRequest saveUserRequest
            , BindingResult bindingResult) {

        // 아이디 중복확인
        if (loginService.selectUserDetail(saveUserRequest.getId()) != null) {
            bindingResult.addError(new FieldError("user", "id", "* 중복되는 아이디가 있습니다."));
            return "login/signUp";
        }

        // TODO input age 란에 문자열 입력 시 defaultMessage 가 영문으로 길게 뜨는 것 막기. errors.properties 가 효과가 없음.
        if (bindingResult.hasErrors()) {
            return "login/signUp";
        }

        loginService.insertUser(saveUserRequest);

        return "redirect:/";
    }

    // 메인 페이지
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }



}

