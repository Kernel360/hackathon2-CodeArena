package sample.codearea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CodeareaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeareaApplication.class, args);
	}

}
