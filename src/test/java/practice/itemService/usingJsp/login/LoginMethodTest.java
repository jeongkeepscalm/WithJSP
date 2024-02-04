package practice.itemService.usingJsp.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.login.service.LoginServiceImpl;

import java.util.List;

@SpringBootTest // 매소드 테스트
public class LoginMethodTest {

    // 메소드 테스트의 경우, 보통 mapper 에 주입시켜 mapper 로 테스트한다.
    @Autowired
    LoginServiceImpl loginService;

    @Test
    void 모든회원조회() {

        try {

            // when, given
            List<User> users = loginService.selectAllUser();
            int allUserCount = loginService.selectAllUserCount();

            // then
            Assertions.assertThat(users.size()).isEqualTo(allUserCount);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void 회원등록() {

            // given
            SaveUserRequest user = new SaveUserRequest("test7"
                    , "test7"
                    , "test7"
                    , "0000000"
                    , "B"
                    , "M"
                    , "ojg@gmail.com"
                    , "N"
                    , "N"
            );

        try {
            // when
            loginService.insertUser(user);

            //then
            User userFound = loginService.selectUserDetail("test2");
            Assertions.assertThat(userFound).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
