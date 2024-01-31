package practice.itemService.usingJsp.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public void typeMismatchException() {
        System.out.println("1111");
    }

}
