package sample.codearea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<UserQuestionScrapHistoryResponseDto> getQuestionScrap() {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. get scrap list by user_id
		return ResponseEntity.ok().build();
	}

	@PutMapping("/questions/{questionId}")
	public ResponseEntity<?> addQuestionScrap(
		@PathVariable Long questionId
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. add to user's scrap list
		return ResponseEntity.ok().build();
	}

	//	DELETE /user/scrap/questions/{questionId}
	@DeleteMapping("/scrap/questions/{questionId}")
	public ResponseEntity<?> removeQuestionScrap(
		@PathVariable Long questionId
	) {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. add to user's scrap list
		return ResponseEntity.ok().build();
	}
}
