package sample.codearea.controller;

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
			@RequestParam int pageSize
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. get scrap list by user_id
		Long userId = getUserIdFromSession();

		UserQuestionScrapHistoryResponseDto responseDto = userService.getUserScrapHistory(userId, currentPage, pageSize);

		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/questions/{questionId}")
	public ResponseEntity<?> addQuestionScrap(
		@PathVariable Long questionId
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. add to user's scrap list

		Long userId = getUserIdFromSession();
		userService.save(userId, questionId);

		return ResponseEntity.ok().build();
	}

	//	DELETE /user/scrap/questions/{questionId}
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> removeQuestionScrap(
		@PathVariable Long questionId
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. delete to user's scrap list

		Long userId = getUserIdFromSession();
		userService.delete(userId, questionId);

		return ResponseEntity.ok().build();
	}

	private Long getUserIdFromSession() {
		// TODO: 세션에서 사용자 ID를 가져오는 로직을 구현해야 함
		return 1L; // 예시로 임시 ID 반환
	}
}
