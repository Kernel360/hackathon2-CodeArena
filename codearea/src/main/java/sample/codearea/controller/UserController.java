package sample.codearea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.codearea.dto.UserMyInfoResponseDto;
import sample.codearea.dto.UserQuestionScrapHistoryResponseDto;
import sample.codearea.dto.UserSignupRequestDto;
import sample.codearea.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(UserSignupRequestDto user) {
		// 1. check if user exists.
		// 2. if exists, create and store session_id to server.
		// 3. else, error response.
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/me")
	public ResponseEntity<UserMyInfoResponseDto> getMyInfo() {
		// 1. check if user session is valid.
		// 2. if valid, return user info
		// 3. else, delete session_id from http header + error response
		return ResponseEntity.ok().build();
	}

	@GetMapping("/scrap/questions")
	public ResponseEntity<UserQuestionScrapHistoryResponseDto> getUserScrapHistory() {
		// 1. check if user session is valid.
		// 2. if valid, get user_id by session_id.
		// 3. get scrap list by user_id
		return ResponseEntity.ok().build();
	}

	@PutMapping("/scrap/questions/{questionId}")
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
