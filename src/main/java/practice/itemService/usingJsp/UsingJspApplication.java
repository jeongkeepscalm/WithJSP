package practice.itemService.usingJsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @SpringBootApplication
 * 스프링 부트 애플리케이션을 구성하는 주요 어노테이션들을 포함하는 편리한 어노테이션.
 * 데이터베이스 연결과 같은 외부 의존성이 없어도 자동으로 실행. (application.properties 내 디비 url 속성 정보는 필요)
**/
@SpringBootApplication
public class UsingJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsingJspApplication.class, args);
	}

}
