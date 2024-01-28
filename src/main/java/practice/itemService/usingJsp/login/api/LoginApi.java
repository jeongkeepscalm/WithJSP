package practice.itemService.usingJsp.login.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.login.service.LoginServiceImpl;

@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginApi {

    LoginServiceImpl loginService;

    @ResponseBody
    @PostMapping("/selectDetail.cm")
    public ResponseEntity duplicateCheckId(@RequestParam("id") String id) {
        return new ResponseEntity<>(loginService.selectUserDetail(id), HttpStatus.OK);
    }


}
