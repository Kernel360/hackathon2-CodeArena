package sample.codearea.common;

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
public class Pagination {

	private Integer currentPage;

	private Integer pageSize;

	private Integer currentElements;

	private Integer totalPage;

	private Long totalElements;
}