package sample.codearea;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.dto.AnswerTestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;
import sample.codearea.service.AnswerService;
import sample.codearea.service.UserService;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {

        UserEntity user1 = UserEntity.builder()
                .email("min@gmail.com")
                .nickname("minky")
                .password("1234")
                .build();

        UserEntity user2 = UserEntity.builder()
                .email("kimkim@gmail.com")
                .nickname("kimkim")
                .password("4321")
                .build();

        UserEntity user3 = UserEntity.builder()
                .email("yang@gmail.com")
                .nickname("yangyang")
                .password("12345678")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        for(int i = 1; i <= 10; i++) {
            QuestionEntity question = QuestionEntity.builder()
                    .user(user1)
                    .title("오류 질문 있습니다 : " + i)
                    .content("만약 위의 사항을 모두 점검하고도 문제가 해결되지 않는다면, 정확한 오류 메시지를 확인하고 이를 바탕으로 문제를 해결해야 합니다.")
                    .views(10 * i)
                    .likes(100 * i)
                    .hates(1 * i)
                    .build();
            questionRepository.save(question);
        }

        for(int i = 11; i <= 20; i++) {
            QuestionEntity question = QuestionEntity.builder()
                    .user(user2)
                    .title("JPA 순환 참조 문제 어떻게 해결? : " + i)
                    .content("JSON이 순환 참조 문제가 발생하여 문제가 생겼습니다. JsonIgnore 등 여러 방법을 사용했으나 해결 방법을 모르겠습니다.")
                    .views(5 * i)
                    .likes(20 * i)
                    .hates(1 * i)
                    .build();
            questionRepository.save(question);
        }

        for(int i = 21; i <= 30; i++) {
            QuestionEntity question = QuestionEntity.builder()
                    .user(user3)
                    .title("Java 다형성 질문 : " + i)
                    .content("다형성에 대해 공부하고 있는데 도저히 모르겠습니다. 더 쉽게 알려주실 분 계신가요?")
                    .views(1000 * i)
                    .likes(1 * i)
                    .hates(10 * i)
                    .build();
            questionRepository.save(question);
        }

        AnswerEntity answer = AnswerEntity.builder()
                .question(questionRepository.findById(1L).orElseThrow())
                .user(user1)
                .content("이거는 무슨 의미인가요?")
                .build();

        answerRepository.save(answer);


//        AnswerRequestDto answer1 = AnswerRequestDto.builder()
//                .userName(user1.getNickname())
//                .content("test")
//                .build();
//
//        AnswerRequestDto answer2 = AnswerRequestDto.builder()
//                .userName(user2.getNickname())
//                .content("안녕")
//                .build();


//        answerService.save(1L, 1L, answer1);
//        answerService.save(1L, 1L, answer2);
//        answerService.save(1L, 1L, answer1);
//        answerService.save(1L, 1L, answer2);
//        answerService.update(responseDto, answer2);
//        answerService.delete(2L);
    }

}
