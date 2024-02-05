package practice.itemService.usingJsp.login.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {

    private String id;
    private String password;
    private String name;
    private Integer birthDay;
    private String bloodType;
    private String sex;
    private String email;
    private String isAdmin = "N";

}
