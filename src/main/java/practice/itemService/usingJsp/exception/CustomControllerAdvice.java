package practice.itemService.usingJsp.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public void typeMismatchException() {
        System.out.println("1111");
    }


//    @ExceptionHandler(CustomBindingResultException.class)
//    public String handleBindingResultException(CustomBindingResultException e, Model model) {
//        BindingResult bindingResult = e.getBindingResult();
//        model.addAttribute("bindingResult", bindingResult);
//        model.addAttribute("loginRequest", bindingResult.getTarget());
//        return "login/loginForm";
//    }
}
