package practice.itemService.usingJsp.login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 키워드가 붙은 필드들을 대상으로하는 생성자를 자동으로 생성해준다.
public enum BloodType {

    A("BloodType A"), B("BloodType B")
    , O("BloodType O"), AB("BloodType AB");

    private final String description;
    /**
     * private final String description;
     * == BloodType(String description) {this.description = description;}
     * */

}
