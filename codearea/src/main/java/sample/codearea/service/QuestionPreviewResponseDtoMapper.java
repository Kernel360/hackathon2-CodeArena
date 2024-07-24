package sample.codearea.service;

import sample.codearea.dto.QuestionPreviewResponseDto;
import sample.codearea.entity.QuestionEntity;

public class QuestionPreviewResponseDtoMapper {

    public static QuestionPreviewResponseDto toDto(QuestionEntity questionEntity) {
        return QuestionPreviewResponseDto.builder()
                .questionId(questionEntity.getId())
                .title(questionEntity.getTitle())
                .nickname(questionEntity.getUser().getNickname())
                .createAt(questionEntity.getCreatedAt())
                .likes(questionEntity.getLikes())
                .hates(questionEntity.getHates())
                .views(questionEntity.getViews())
                .build();
    }
}
