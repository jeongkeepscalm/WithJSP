package practice.itemService.usingJsp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@RequiredArgsConstructor
public class CustomNumberFormatException {

    private final BindingResult bindingResult;

}
