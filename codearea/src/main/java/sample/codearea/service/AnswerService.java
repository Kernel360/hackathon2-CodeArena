package sample.codearea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerTestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * 한 사람당 하나의 답변만 작성할 수 있다.
 */
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public Optional<AnswerEntity> findById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public List<AnswerEntity> findAll() {
        return answerRepository.findAll();
    }

    public AnswerEntity save(AnswerTestDto answerRequestDto) {
        UserEntity user = userRepository.findById(answerRequestDto.getUserId()).get();

        AnswerEntity answer = AnswerEntity.builder()
                .user(user)
                .content(answerRequestDto.getContent())
                .build();

        AnswerEntity save = answerRepository.save(answer);
        return save;
    }

    public void update(Long answerId, AnswerTestDto answerRequestDto) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();

        answer.setContent(answerRequestDto.getContent());
        answerRepository.save(answer);
    }

    public void delete(Long answerId) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();
        answerRepository.delete(answer);
    }


}
