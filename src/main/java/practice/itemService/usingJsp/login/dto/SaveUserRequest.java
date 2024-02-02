package practice.itemService.usingJsp.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import practice.itemService.usingJsp.exception.CustomNumberFormatException;

@Data
@Slf4j
public class SaveUserRequest {

    @NotBlank(message = "* 아이디를 입력해주세요.")
    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$"
            , message = "* 숫자, 문자를 포함한 4자리 이상의 비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "* 이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "* 생년월일 8자리를 입력해주세요.")
    @Size(min = 8, message = "* 생년월일 8자리를 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "* 숫자를 입력해주세요.")
    private String birthday;

    @NotBlank(message = "* 혈액형을 선택해주세요.")
    private String bloodType;

    @NotNull(message = "* 성별을 선택해주세요.")
    private String sex;

    @NotBlank(message = "* 이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "* 이메일 형식에 맞지 않습니다.")
    private String email;

    private String isAdmin = "N";
    private String adminPassword;



}
