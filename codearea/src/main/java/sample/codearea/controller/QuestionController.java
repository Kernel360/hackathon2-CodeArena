package sample.codearea.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.codearea.dto.PaginatedQuestionPreviewListRequestDto;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@GetMapping("")
	public ResponseEntity<?> getQuestions(
		@RequestParam(value = "title") Optional<String> title,
		@RequestParam(value = "content") Optional<String> content,
		@RequestParam(value = "email") Optional<String> email,
		@RequestParam(value = "nickname") Optional<String> nickname,
		@RequestBody @Valid PaginatedQuestionPreviewListRequestDto requestDto
	) {
		//	GET /questions?title={?}&content={?}&nickname={?}&email={?}

		//  1. 4개의 Request Param 중 반드시 1개만 들어 옵니다
		//     ex) /question?title=java
		//     ex) /question?nickname=김영한
		//     - 만약 검색 조건이 2개 이상 들어오면, 예외를 발생시킵니다.

		//  2. reqeustDto에는 정렬 조건이 들어옵니다.
		//     조회수, 싫어요 수, 좋아요 수로 정렬합니다.

		return ResponseEntity.ok(requestDto);
	}
}
