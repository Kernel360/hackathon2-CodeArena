package sample.codearea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// TODO: 투표, 스크랩 처리 완료 되면 정보 추가 필요
	@GetMapping("/my-page")
	public ResponseEntity<UserMyInfoResponseDto> myPage(HttpServletRequest httpServletRequest) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getUserInfo(httpServletRequest));
	}
}
