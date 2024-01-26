package practice.itemService.usingJsp.login.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;

@Data
@Slf4j
public class SaveUserRequest {

    @NotBlank(message = "* 아이디를 입력해주세요.")
    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
            , message = "* 숫자, 문자를 포함한 8자리 이상의 비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "* 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "* 나이를 입력해주세요.")
    @Positive(message = "* 유효한 나이를 입력해주세요.")
    @Range(min = 19, max = 99, message = "* 19세 ~ 99세 전용입니다.")
    private Integer age;

    @NotBlank(message = "* 혈액형을 선택해주세요.")
    private String bloodType;

    @NotNull(message = "* 성별을 선택해주세요.")
    private String sex;

    @NotBlank(message = "* 이메일을 입력해주세요.")
    private String email;

    // Integer type 인데 문자열을 입력했을 경우를 위한 코드..
    public void setAge(String age) {
        try {
            this.age = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            log.info("e : {}", e);
        }
    }
}
