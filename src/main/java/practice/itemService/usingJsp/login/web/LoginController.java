package practice.itemService.usingJsp.login.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.itemService.usingJsp.AES256;
import practice.itemService.usingJsp.AdminPasswordConst;
import practice.itemService.usingJsp.SessionConst;
import practice.itemService.usingJsp.exception.CustomBindingResultException;
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
    public String loginForm() {
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

        } catch (CustomBindingResultException e) {

            if (e.getBindingResult().hasErrors()) {
                return "login/loginForm";
            }

        }

        return "redirect:" + redirectURL;

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

            // 관리자 체크 되어 있을 경우
            if (saveUserRequest.getIsAdmin().equals("Y")) {

                // 관리자 비밀번호를 입력하지 않았을 경우
                if (saveUserRequest.getAdminPassword().equals("")) {
                    bindingResult.addError(new FieldError("user", "adminPassword", "* 비밀번호를 입력해 주세요"));
                    return "login/signUp";
                }

                // 관리자 비밀번호가 맞지 않을 경우
                if (!saveUserRequest.getAdminPassword().equals(AdminPasswordConst.ADMIN_PASSWORD)) {
                    bindingResult.addError(new FieldError("user", "adminPassword", "* 비밀번호가 맞지 않습니다."));
                    return "login/signUp";
                }

            }

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

            // 회원 등록 완료 alert
            redirectAttributes.addAttribute("completedWithSigningUp", true);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "redirect:/";

    }




}

