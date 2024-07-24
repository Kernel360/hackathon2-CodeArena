package sample.codearea.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDto {

    private String userName;
    private String title;
    private String content;
}
