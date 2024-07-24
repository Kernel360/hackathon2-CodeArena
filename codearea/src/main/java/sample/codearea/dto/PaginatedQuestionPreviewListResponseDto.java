package sample.codearea.dto;

import java.util.List;
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
public class PaginatedQuestionPreviewListResponseDto {

	private List<QuestionPreviewResponseDto> questionPreviews;

	private PaginationResponseDto pagination;
}
