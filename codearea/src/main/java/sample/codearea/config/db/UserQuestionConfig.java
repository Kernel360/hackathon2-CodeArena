package sample.codearea.config.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

@Configuration
public class UserQuestionConfig {

	/**
	 * NOTE: DB 테스트는 여기서 모두 진행합니다.
	 *   초기화할 데이터 목록입니다.
	 *    1. 사용자 (2명)
	 *    2. 질문 (사용자당 10개)
	 *    3. 사용자 - 질문 스크랩 (사용자당 2개)
	 */
	@Bean
	CommandLineRunner commandLineRunner(
			QuestionRepository questionRepository,
			UserRepository userRepository,
			AnswerRepository answerRepository
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

			AnswerEntity answer = AnswerEntity.builder()
					.user(user1)
					.question(question1)
					.content("test")
					.build();

			answerRepository.save(answer);
		};
	}

}