package practice.itemService.usingJsp.login.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import practice.itemService.usingJsp.login.service.LoginServiceImpl;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class LoginApi {

    private final LoginServiceImpl loginService;

    // 매개변수에 @RequestParam 을 써서 값이 안 넘어오는 문제를 겪음.
    // @RequestParam : 쿼리스트링으로 데이터가 전송되며 GetMapping 에 쓰인다.
    @ResponseBody
    @PostMapping("/selectDetail.cm")
    public ResponseEntity selectUserDetail(@RequestBody Map<String, String> map) {
        return new ResponseEntity<>(loginService.selectUserDetail(map.get("id")), HttpStatus.OK);
    }


}
