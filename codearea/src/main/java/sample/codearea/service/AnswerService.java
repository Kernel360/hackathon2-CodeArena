package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.codearea.constant.SessionConst;
import sample.codearea.constant.voteStatus;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

import java.util.ArrayList;
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
    public List<AnswerResponseDto> findAll(Long questionId) {
        List<AnswerEntity> byQuestionId = answerRepository.findByQuestionIdOrderByCreatedAtDesc(questionId);

        List<AnswerResponseDto> answerResponseDtoList = new ArrayList<>();
        for (AnswerEntity answerEntity : byQuestionId) {
            answerResponseDtoList.add(AnswerConverter.toDto(answerEntity, voteStatus.VOTE_STATUS_NOT_VOTED, false));
        }
        return answerResponseDtoList;
    }

    public AnswerResponseDto save(Long questionId, AnswerRequestDto answerRequestDto, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        UserEntity user = userRepository.findById(loginId).get();
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
        AnswerResponseDto answerResponseDto = answerConverter.toDto(save, voteStatus.VOTE_STATUS_NOT_VOTED, false);

        return answerResponseDto;
    }

    public void update(Long answerId, AnswerRequestDto answerRequestDto, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("not found answer"));

        if(loginId == answer.getUser().getId()){
            answer.setContent(answerRequestDto.getContent());
            answerRepository.save(answer);
            return;
        }
        throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
    }

    public void delete(Long answerId, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("not found answer"));

        if(loginId == answer.getUser().getId()){
            answerRepository.delete(answer);
            return;
        }
        throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
    }

    private Long getLoginId(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Long loginId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return loginId;
    }

}
