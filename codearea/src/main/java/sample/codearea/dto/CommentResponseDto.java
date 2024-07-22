package sample.codearea.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private String nickname;
    private LocalDateTime modifiedAt;
    private String content;

}
