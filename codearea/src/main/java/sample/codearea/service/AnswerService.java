package sample.codearea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public AnswerEntity save(AnswerRequestDto answerRequestDto) {
//        UserEntity user = userRepository.findById().get();
//
//        user.getNickname()
//
        return null;
    }

    public void update(AnswerRequestDto answerEntity) {

    }

    public void delete(Long answerId) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();
        answerRepository.delete(answer);
    }


}
