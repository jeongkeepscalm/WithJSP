package practice.itemService.usingJsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import practice.itemService.usingJsp.exception.CustomNoFileException;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class RememberMe {

    private static final String CREDENTIALS_FILE = "credentials.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int MINUTES_TO_MAINTAIN = 30;


    // 암호화 된 아이디, 비밀번호를 파일에 저장한다.
    public static void writeCredentialsText (LoginRequest loginRequest) {

        try {

            String requestedId = loginRequest.getId();

            String encryptedPassword = loginRequest.getPassword(); // 서비스 로직에서 이미 비밀번호를 암호화 해놓은 상태.
            String encryptedId = AES256.encrypt(requestedId);
            String currentTime = LocalDateTime.now().format(FORMATTER);

            Path path = Paths.get(CREDENTIALS_FILE); // 파일 경로 설정
            Files.write(path, (encryptedId + "\n" + encryptedPassword + "\n" + currentTime).getBytes()); // 파일 생성

        } catch (IOException e) {
            log.error("An error occurred while writing to the file: {}", e.getMessage());
        }

    }

    // 암호화 된 아이디, 비밀번호가 있는 파일을 가져와 작업한다.
    public static void readCredentialsText(User user) throws CustomNoFileException {

        try {

            List<String> lines = Files.readAllLines(Paths.get(CREDENTIALS_FILE));

            String savedId = AES256.decrypt(lines.get(0));
            String savedPassword = AES256.decrypt(lines.get(1));
            LocalDateTime savedTime = LocalDateTime.parse(lines.get(2), FORMATTER);

            // 저장된 credentials.txt 파일이 특정시간 지나면, 파일을 삭제한다.
            if (LocalDateTime.now().isAfter(savedTime.plusMinutes(MINUTES_TO_MAINTAIN))) {
                Files.delete(Paths.get(CREDENTIALS_FILE));
                throw new CustomNoFileException(CREDENTIALS_FILE);
            }

            user.setId(savedId);
            user.setPassword(savedPassword);

            // NoSuchFileException 은 IOException 의 하위 클래스이기 때문에, 먼저 catch 문에서 잡아줘야 컴파일 에러가 뜨질 않는다.
        } catch (NoSuchFileException e) {
            throw new CustomNoFileException(CREDENTIALS_FILE);
        } catch (IOException e) {
            log.error("An error occurred while reading to the file : {}", e.getMessage());
        }
    }



}
