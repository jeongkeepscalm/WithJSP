package practice.itemService.usingJsp.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    @ExceptionHandler(TypeMismatchException.class)
    public void typeMismatchException() {
        System.out.println("1111");
    }

}
