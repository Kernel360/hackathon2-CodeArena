package sample.codearea.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentTestDto {

    private Long userId;
    private Long answerId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String content;
}

