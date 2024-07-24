package sample.codearea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.codearea.dto.UserLoginRequestDto;
import sample.codearea.dto.UserMyInfoResponseDto;
import sample.codearea.dto.UserSignupRequestDto;
import sample.codearea.service.UserService;
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserSignupRequestDto userSignupRequestDto) {
		return userService.signUp(userSignupRequestDto);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletRequest httpServletRequest) {
		try {
			return userService.login(userLoginRequestDto, httpServletRequest);
		}catch(IllegalArgumentException e){
			log.error("login error: {}", e.getMessage());

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(IllegalAccessException e){
			log.error("login error: {}", e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.IM_USED);
		}
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		if(session != null){
			session.invalidate();
			session= httpServletRequest.getSession(false);
		}
		return ;
	}

	@GetMapping("/me")
	public ResponseEntity<UserMyInfoResponseDto> getMyInfo() {
		// 1. check if user session is valid.
		// 2. if valid, return user info
		// 3. else, delete session_id from http header + error response
		return ResponseEntity.ok().build();
	}
}
