package practice.itemService.usingJsp.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
public class NaNException extends RuntimeException {

    private final BindingResult bindingResult;
}
