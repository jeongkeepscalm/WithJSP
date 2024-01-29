package practice.itemService.usingJsp.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;

@Data
@Slf4j
public class LoginRequest {

    @NotBlank(message = "* 아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "* 비밀번호를 입력해주세요.")
    private String password;


}
