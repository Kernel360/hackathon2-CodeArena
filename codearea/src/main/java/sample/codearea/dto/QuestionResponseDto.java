package sample.codearea.dto;

import lombok.*;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.constant.voteStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDto {

    private UserQuestionCK userQuestionCK;

    private String userName;
    private LocalDateTime createdAt;
    private Integer views;
    private String title;
    private String content;
    private voteStatus voteStatus;

}
