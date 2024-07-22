package sample.codearea.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionPreviewResponseDto {

	private Long questionId;

	private String title;

	private String nickname;

	private LocalDateTime createAt;

	private Integer likes;

	private Integer hates;

	private Integer views;
}
