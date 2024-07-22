package sample.codearea.service;

import org.springframework.stereotype.Service;
import sample.codearea.dto.CommentRequestDto;
import sample.codearea.entity.CommentEntity;


@Service
public class CommentConverter {

    public CommentRequestDto toDto(CommentEntity entity) {
        return CommentRequestDto.builder()
                .userName(entity.getUser().getNickname())
                .content(entity.getContent())
                .build();
    }
}
