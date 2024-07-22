package sample.codearea;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import sample.codearea.dto.AnswerTestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.UserRepository;
import sample.codearea.service.AnswerService;
import sample.codearea.service.UserService;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
//    private final UserService userService;
    private final AnswerService answerService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("init data");

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


        AnswerTestDto answer1 = AnswerTestDto.builder()
                .userId(user1.getId())
                .userName(user1.getNickname())
                .content("test")
                .build();

        AnswerTestDto answer2 = AnswerTestDto.builder()
                .userId(user1.getId())
                .userName(user1.getNickname())
                .content("안녕")
                .build();


        AnswerEntity savedAnswer = answerService.save(answer1);
        answerService.save(answer2);
        answerService.update(savedAnswer.getId(), answer2);
        answerService.delete(2L);
    }

}
