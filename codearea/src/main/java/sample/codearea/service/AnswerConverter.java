package sample.codearea.service;

import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.entity.AnswerEntity;

public class AnswerConverter {

    public AnswerResponseDto toDto(AnswerEntity entity) {
        return AnswerResponseDto.builder()
                .modifiedAt(entity.getUpdatedAt())
                .content(entity.getContent())
                .build();
    }

}
