package sample.codearea.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.codearea.dto.UserMyInfoResponseDto;
import sample.codearea.dto.UserSignupRequestDto;
import sample.codearea.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(
		@RequestBody @Valid UserSignupRequestDto user
	) {
		// 3. check if email is present. (email = UNIQUE)
		// 2. check if nickname is present. (nickname = UNIQUE)
		//    if present, add additional tags (ex. minky --> minky#142)
		// 3. save with encoded password
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// 로그인은 Authorization Header에서 데이터를 받습니다.
	// https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		// 1. check if user exists.
		// 2. if exists, create and store session_id to server.
		// 3. else, error response.
		return ResponseEntity.ok().build();
	}

	@GetMapping("/me")
	public ResponseEntity<UserMyInfoResponseDto> getMyInfo() {
		// 1. check if user session is valid.
		// 2. if valid, return user info
		// 3. else, delete session_id from http header + error response
		return ResponseEntity.ok().build();
	}
}
