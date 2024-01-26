package practice.itemService.usingJsp.login.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private String id;
    private String password;
    private String name;
    private Integer age;

    private BloodType bloodType;
    private List<String> sex;


}
