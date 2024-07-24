package sample.codearea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.CommentRepository;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;
import sample.codearea.service.AnswerService;

@EnableJpaAuditing
@SpringBootApplication
public class CodeareaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeareaApplication.class, args);
	}

	@Bean
	TestDataInit testDataInit(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CommentRepository commentRepository){
		return new TestDataInit(userRepository, questionRepository, answerRepository, commentRepository);
	}

}
