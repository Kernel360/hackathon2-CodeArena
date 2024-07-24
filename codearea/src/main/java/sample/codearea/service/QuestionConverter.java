package sample.codearea.service;

import org.springframework.stereotype.Service;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.dto.QuestionResponseDto;
import sample.codearea.entity.QuestionEntity;

@Service
public class QuestionConverter {

    public QuestionResponseDto toDto(QuestionEntity questionEntity, Long userId) {
        return QuestionResponseDto.builder()
                .userId(userId)
                .questionId(questionEntity.getId())
                .userName(questionEntity.getUser().getNickname())
                .createdAt(questionEntity.getCreatedAt())
                .views(questionEntity.getViews())
                .title(questionEntity.getTitle())
                .content(questionEntity.getContent())
                .build();
    }
}