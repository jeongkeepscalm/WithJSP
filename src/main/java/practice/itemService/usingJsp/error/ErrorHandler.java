package practice.itemService.usingJsp.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandler implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object errorStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // error 있다면,
        if (errorStatus != null) {

            int statusCode = Integer.valueOf(errorStatus.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            } else {
                return "errors/error";
            }
        }

        return "errors/error";

    }
}
