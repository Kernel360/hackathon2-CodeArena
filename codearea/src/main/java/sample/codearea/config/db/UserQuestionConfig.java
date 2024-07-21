package sample.codearea.config.db;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

/**
 * DB에 관계가 잘 설정되는지 테스트하기 위한 용도이며
 * 추후 제거될 Class입니다.
 * - 주 테스트 : Scrap이 작동하는지. ( UserService.scrap(question) )
 */

@Configuration
public class UserQuestionConfig {

	// DB 테스트용으로 작성하였습니다. (@Bean 주석 비활성화시 DB에 Insert 수행)
	// @Bean
	CommandLineRunner commandLineRunner(
		QuestionRepository questionRepository,
		UserRepository userRepository
	) {
		return args -> {
			// DB test

			UserEntity user1 = UserEntity.builder()
										 .email("min@gmail.com")
										 .nickname("minky")
										 .password("1234")
										 .build();

			userRepository.save(user1);

			QuestionEntity question1 = QuestionEntity.builder()
													 .user(user1)
													 .title("JPA 엔티티 설정 방식 질문")
													 .content("어떻게 하나요?")
													 .build();

			questionRepository.save(question1);

			// do scrap
			user1.getQuestionScraps().add(question1);
			// save를 해야만 테이블에 반영되는가...?
			userRepository.save(user1);
		};
	}
}
