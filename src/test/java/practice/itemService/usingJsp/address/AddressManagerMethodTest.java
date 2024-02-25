package practice.itemService.usingJsp.address;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 매소드 테스트
public class AddressManagerMethodTest {

    @Autowired
    AddressManager addressManager;

    @Test
    void 압축풀기() throws Exception {
        addressManager.extractZip();
    }

    @Test
    void 파일읽고데이터저장() throws Exception {
        addressManager.readFileAndDBProcess();
    }

}
