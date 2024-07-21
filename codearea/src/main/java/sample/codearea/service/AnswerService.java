package sample.codearea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Optional<AnswerEntity> findById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public List<AnswerEntity> findAll() {
        return answerRepository.findAll();
    }

    public AnswerEntity save(AnswerEntity answerEntity) {
        return answerRepository.save(answerEntity);
    }

    public void update(AnswerEntity answerEntity) {
        
    }

    public void delete(Long answerId) {
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow();
        answerRepository.delete(answer);
    }


}
