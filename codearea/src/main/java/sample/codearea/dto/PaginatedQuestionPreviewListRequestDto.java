package sample.codearea.dto;

import jakarta.validation.Valid;
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
public class PaginatedQuestionPreviewListRequestDto {

	@Valid
	private SortRequestDto sort;

	@Valid
	private PaginationRequestDto pagination;
}
