package sample.codearea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 한 사람당 하나의 답변만 작성할 수 있다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerConverter answerConverter;

    public Optional<AnswerEntity> findById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public List<AnswerResponseDto> findAll() {
        return answerRepository.findAll().stream()
                .map(answerConverter::toDto)
                .collect(Collectors.toList());
    }

    public AnswerResponseDto save(Long userId, Long questionId, AnswerRequestDto answerRequestDto) {
        UserEntity user = userRepository.findById(userId).get();
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Not found Question"));

        // request dto to entity
        AnswerEntity answer = AnswerEntity.builder()
                .question(question)
                .user(user)
                .content(answerRequestDto.getContent())
                .build();

        AnswerEntity save = answerRepository.save(answer);

        // entity to response dto
        AnswerResponseDto answerResponseDto = answerConverter.toDto(save);

        return answerResponseDto;
    }

    public void update(Long answerId, AnswerRequestDto answerRequestDto) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();

        answer.setContent(answerRequestDto.getContent());
        answerRepository.save(answer);
    }

    public void delete(Long answerId) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();
        answerRepository.delete(answer);
    }


}
