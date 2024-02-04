package practice.itemService.usingJsp.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class CustomBindingResultException extends RuntimeException{

    private final BindingResult bindingResult;

    public CustomBindingResultException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }


}
