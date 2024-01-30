package practice.itemService.usingJsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @SpringBootApplication
 * 스프링 부트 애플리케이션을 구성하는 주요 어노테이션들을 포함하는 편리한 어노테이션.
 * 데이터베이스 연결과 같은 외부 의존성이 없어도 자동으로 실행. (application.properties 내 디비 url 속성 정보는 필요)
**/
@SpringBootApplication
public class UsingJspApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UsingJspApplication.class, args);
	}

	// JSP 는 WAR 로 해야되는 걸 늦게 알아서 JAR -> WAR 설정 변경 후 코드 추가.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UsingJspApplication.class);
	}

}
