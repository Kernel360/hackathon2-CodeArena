package sample.codearea.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {
    private Long userId;

    private Long answerId;

    @NotEmpty
    private String content;

}
