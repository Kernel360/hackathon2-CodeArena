package sample.codearea.dto;

import lombok.*;
import sample.codearea.constant.voteStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDto {

    private Long userId;
    private Long answerId;
    private String nickname;
    private LocalDateTime modifiedAt;
    private String content;
    private voteStatus voteStatus;
    private Boolean scrapStatus;

}
