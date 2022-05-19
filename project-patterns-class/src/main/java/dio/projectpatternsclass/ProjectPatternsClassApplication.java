package dio.projectpatternsclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjectPatternsClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPatternsClassApplication.class, args);
	}

}
