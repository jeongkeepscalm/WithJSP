package practice.itemService.usingJsp.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.NoSuchFileException;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public void typeMismatchException() {
        System.out.println("1111");
    }


    @ExceptionHandler(NoSuchFileException.class)
    public String noSuchFileException() {
        log.info("2222");
        return "login/loginForm";
    }


}
