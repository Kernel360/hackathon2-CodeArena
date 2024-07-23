package sample.codearea.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.codearea.dto.PaginatedQuestionPreviewListRequestDto;
import sample.codearea.dto.PaginatedQuestionPreviewListResponseDto;
import sample.codearea.dto.PaginationResponseDto;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.service.QuestionMapper;
import sample.codearea.service.QuestionService;

@Controller
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:8080/questions")
public class QuestionController {

	private final QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}

	// * 여러개의 Reqeust Param중에서 1개만 들어옴을 어떻게 보장해야 할지?
	//	     ex) GET /questions?title={?}&content={?}&nickname={?}&email={?}
	//       1. 4개의 Request Param 중 반드시 1개만 들어 온다.
	//          ex) /question?title=java
	//          ex) /question?nickname=김영한
	//       2. 만약 검색 조건이 2개 이상 들어오면, 예외를 발생

	@PostMapping("")
	public ResponseEntity<?> getQuestions(
		@RequestParam(value = "category") Optional<String> searchCategory,
		@RequestParam(value = "search") Optional<String> searchString,
		@RequestBody @Valid PaginatedQuestionPreviewListRequestDto requestDto
	) {

		PaginatedQuestionPreviewListResponseDto responseDto = new PaginatedQuestionPreviewListResponseDto();

		Sort sortStrategy = Sort.by(Direction.DESC, requestDto.getSort().getTarget());

		if ( searchCategory.isPresent() && searchString.isPresent() ) {
			Page<QuestionEntity> result = questionService
				.getQuestionsByContainingStringWithPagingAndSorting(
					searchCategory.get(),
					searchString.get(),
					requestDto.getPagination().getCurrentPage(),
					requestDto.getPagination().getPageSize(),
					sortStrategy
				);

			responseDto.setPagination(
				PaginationResponseDto.builder()
									 .currentPage(result.getNumber())
									 .totalPage(result.getTotalPages())
									 .build()
			);

			responseDto.setQuestionPreviews(
				result.getContent()
					  .stream().map(QuestionMapper::toQuestionPreviewDto)
					  .toList()
			);

			return ResponseEntity.ok(responseDto);
		}

		Page<QuestionEntity> result = questionService
			.getQuestionsWithPagingAndSorting(
				requestDto.getPagination().getCurrentPage(),
				requestDto.getPagination().getPageSize(),
				sortStrategy
			);

		responseDto.setPagination(
			PaginationResponseDto.builder()
								 .currentPage(result.getNumber())
								 .totalPage(result.getTotalPages())
								 .build()
		);
		responseDto.setQuestionPreviews(
			result.getContent()
				  .stream().map(QuestionMapper::toQuestionPreviewDto)
				  .toList()
		);

		return ResponseEntity.ok(responseDto);
	}
}
