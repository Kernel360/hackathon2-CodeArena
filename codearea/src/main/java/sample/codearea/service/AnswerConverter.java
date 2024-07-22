package sample.codearea.service;

import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.entity.AnswerEntity;

public class AnswerConverter {

    public AnswerRequestDto toDto(AnswerEntity entity) {
        return AnswerRequestDto.builder()
                .userName(entity.getUser().getNickname())
                .content(entity.getContent())
                .build();
    }

}
