package sample.codearea.service;

import org.springframework.stereotype.Service;
import sample.codearea.constant.voteStatus;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.entity.AnswerEntity;

@Service
public class AnswerConverter {

    public static AnswerResponseDto toDto(AnswerEntity entity, voteStatus voteStatus, boolean scrapStatus) {
        return AnswerResponseDto.builder()
                .userId(entity.getUser().getId())
                .answerId(entity.getId())
                .nickname(entity.getUser().getNickname())
                .modifiedAt(entity.getUpdatedAt())
                .content(entity.getContent())
                .voteStatus(voteStatus)
                .scrapStatus(scrapStatus)
                .build();
    }

}
