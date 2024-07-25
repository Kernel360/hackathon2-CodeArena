package sample.codearea.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.codearea.dto.UserQuestionScrapHistoryResponseDto;
import sample.codearea.service.UserService;

@Controller
@RequestMapping("/user/scrap")
public class UserScrapController {

	private final UserService userService;

	public UserScrapController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/questions")
	public ResponseEntity<UserQuestionScrapHistoryResponseDto> getQuestionScrap(
			@RequestParam int currentPage,
			@RequestParam int pageSize,
			HttpServletRequest httpServletRequest
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. get scrap list by user_id
//		Long userId = getUserIdFromSession();

		UserQuestionScrapHistoryResponseDto responseDto = userService.getUserScrapHistory(httpServletRequest, currentPage, pageSize);

		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/questions/{questionId}")
	public ResponseEntity<?> addQuestionScrap(
		@PathVariable Long questionId,
		HttpServletRequest httpServletRequest
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. add to user's scrap list

//		Long userId = getUserIdFromSession();
		userService.save(httpServletRequest, questionId);

		return ResponseEntity.ok().build();
	}

	//	DELETE /user/scrap/questions/{questionId}
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> removeQuestionScrap(
		@PathVariable Long questionId,
		HttpServletRequest httpServletRequest
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. delete to user's scrap list

//		Long userId = getUserIdFromSession();
		userService.delete(httpServletRequest, questionId);

		return ResponseEntity.ok().build();
	}
/*
	private Long getUserIdFromSession(HttpServletRequest httpServletRequest) {
		// TODO: 세션에서 사용자 ID를 가져오는 로직을 구현해야 함
		return 1L; // 예시로 임시 ID 반환
	}*/
}
