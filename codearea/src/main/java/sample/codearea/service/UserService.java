package sample.codearea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.dto.PaginationResponseDto;
import sample.codearea.dto.QuestionPreviewResponseDto;
import sample.codearea.dto.UserQuestionScrapHistoryResponseDto;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * - NOTE: scrap은 질문당 1개만 가능.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public UserQuestionScrapHistoryResponseDto getUserScrapHistory(Long userId, int currentPage, int pageSize) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, user.getQuestionScraps().size());

        List<QuestionEntity> pagedScraps = user.getQuestionScraps().subList(start, end);

        List<QuestionPreviewResponseDto> questionPreviews = pagedScraps.stream()
                .map(QuestionPreviewResponseDtoMapper::toDto)
                .collect(Collectors.toList());

        int totalPage = (int) Math.ceil((double) user.getQuestionScraps().size() / pageSize);

        PaginationResponseDto pagination = new PaginationResponseDto();
        pagination.setCurrentPage(currentPage);
        pagination.setTotalPage(totalPage);

        UserQuestionScrapHistoryResponseDto responseDto = new UserQuestionScrapHistoryResponseDto();
        responseDto.setPagination(pagination);
        responseDto.setQuestionPreviews(questionPreviews);

        return responseDto;
    }

    public void save(Long userId, Long questionId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        user.getQuestionScraps().add(question);
        userRepository.save(user);
    }
    public void delete(Long userId, Long questionId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        user.getQuestionScraps().remove(question);
        userRepository.save(user);
    }

}
