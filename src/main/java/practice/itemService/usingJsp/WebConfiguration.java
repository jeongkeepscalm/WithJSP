package practice.itemService.usingJsp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import practice.itemService.usingJsp.interceptor.LoginCheckInterceptor;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login" , "/logOut" , "/signUp"
                        , "/css/**" , "/js/**" , "/*.ico" , "/error"
                );
    }

}
