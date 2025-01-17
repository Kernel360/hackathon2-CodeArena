package sample.codearea.dto;

import jakarta.validation.constraints.Pattern;
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
public class SortRequestDto {

	static final String DEFAULT_SORT_STRATEGY = "createdAt";

	@Pattern(
		regexp = "^(createdAt|views|likes|hates)$",
		message = "정렬이 불가능한 옵션입니다"
	)
	private String target = DEFAULT_SORT_STRATEGY;
}
