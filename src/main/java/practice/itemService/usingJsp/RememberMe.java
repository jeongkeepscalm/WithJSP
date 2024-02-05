package practice.itemService.usingJsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class RememberMe {

    public static void writeCredentialsText (LoginRequest loginRequest) {

        try {

            String requestedId = loginRequest.getId();
            String requestedPassword = loginRequest.getPassword();

            String encryptedId = AES256.encrypt(requestedId);
            String encryptedPassword = AES256.encrypt(requestedPassword);

            Path path = Paths.get("credentials.txt"); // 파일 경로 설정
            Files.write(path, (encryptedId + "\n" + encryptedPassword).getBytes()); // 파일 생성

            log.info("id : {}", Files.readAllLines(path).get(0));
            log.info("password : {}", Files.readAllLines(path).get(1));

        } catch (IOException e) {
            log.error("An error occurred while writing to the file: {}", e.getMessage());
        }

    }

    public static void readCredentialsText(User user) {

        try {

            Path path = Paths.get("credentials.txt");

            Files.readAllLines(path).stream().forEach(v -> {
                log.info("v : {}", v);
            });

            List<String> lines = Files.readAllLines(path);

            String savedId = AES256.decrypt(lines.get(0));
            String savedPassword = AES256.decrypt(lines.get(1));

            user.setId(savedId);
            user.setPassword(savedPassword);

            log.info("user : {}", user);

            // NoSuchFileException 은 IOException 의 하위 클래스이기 때문에, 먼저 catch 문에서 잡아줘야 컴파일 에러가 뜨질 않는다.
        } catch (NoSuchFileException e) {
            log.error("Credentials file not found : {}", e.getMessage());
        } catch (IOException e) {
            log.error("An error occurred while reading to the file : {}", e.getMessage());
        }
    }



}
