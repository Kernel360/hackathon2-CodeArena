package sample.codearea.service;

import org.springframework.stereotype.Service;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.entity.AnswerEntity;

@Service
public class AnswerConverter {

    public AnswerResponseDto toDto(AnswerEntity entity) {
        return AnswerResponseDto.builder()
                .answerId(entity.getId())
                .nickname(entity.getUser().getNickname())
                .modifiedAt(entity.getUpdatedAt())
                .content(entity.getContent())
                .build();
    }

}
