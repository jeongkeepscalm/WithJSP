package practice.itemService.usingJsp.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomNoFileException.class)
    public String customNoFileException(CustomNoFileException e) {
        log.info("errorMessage : {}", "File not found : " + e.getFileName());
        return "login/loginForm";
    }


}
