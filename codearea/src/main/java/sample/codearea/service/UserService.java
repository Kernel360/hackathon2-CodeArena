package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sample.codearea.constant.SessionConst;
import sample.codearea.dto.*;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * - NOTE: scrap은 질문당 1개만 가능.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserQuestionScrapHistoryResponseDto getUserScrapHistory(Long userId, int currentPage, int pageSize) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, user.getQuestionScraps().size());

        List<QuestionEntity> pagedScraps = user.getQuestionScraps().subList(start, end);

        List<QuestionPreviewResponseDto> questionPreviews = pagedScraps.stream()
                .map(QuestionPreviewResponseDtoMapper::toDto)
                .collect(Collectors.toList());

        int totalPage = (int) Math.ceil((double) user.getQuestionScraps().size() / pageSize);

        PaginationResponseDto pagination = new PaginationResponseDto();
        pagination.setCurrentPage(currentPage);
        pagination.setTotalPage(totalPage);

        UserQuestionScrapHistoryResponseDto responseDto = new UserQuestionScrapHistoryResponseDto();
        responseDto.setPagination(pagination);
        responseDto.setQuestionPreviews(questionPreviews);

        return responseDto;
    }

    public void save(Long userId, Long questionId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        user.getQuestionScraps().add(question);
        userRepository.save(user);
    }
    public void delete(Long userId, Long questionId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        user.getQuestionScraps().remove(question);
        userRepository.save(user);
    }

    public ResponseEntity<?> signUp(UserSignupRequestDto userSignupRequestDto) {
        var data = userSignupRequestDto;

        // password hash encryption
        String encryptedPassword = bCryptPasswordEncoder.encode(data.getPassword());
        data.setPassword(encryptedPassword);

        if(getUserByNickname(data.getNickname()).isPresent() || getUserByEmail(data.getEmail()).isPresent()){  // nickname or email is already in DB
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body(null);
        }else{
            // save to DB
            userRepository.save(userSignupRequestDto.toEntity(data));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(data);
        }
    }

    public ResponseEntity<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto, HttpServletRequest httpServletRequest) throws IllegalAccessException {

        var data = userLoginRequestDto;

        UserEntity userEntity = getUserByEmail(data.getEmail()).orElseThrow(() -> new IllegalArgumentException("user not found"));
        // // TODO: 로그인 성공시 id, email, nickname 리턴하도록 수정
        HttpSession session = httpServletRequest.getSession();
        Object loginUserId = session.getAttribute(SessionConst.LOGIN_USER);



        // session check and compare if login request email and email saved on session are same
        if(loginUserId != null && data.getEmail().equals(userEntity.getEmail())){
            throw new IllegalAccessException("already login");
//            return ResponseEntity.status(HttpStatus.IM_USED)
//                    .body(data);
        }

        // user info check by email and check password
        if(userEntity != null && bCryptPasswordEncoder.matches(data.getPassword(), userEntity.getPassword())){

            session.setAttribute(SessionConst.LOGIN_USER, userEntity.getId());

            UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
            userLoginResponseDto.setId(userEntity.getId());
            userLoginResponseDto.setNickname(userEntity.getNickname());
            userLoginResponseDto.setEmail(userEntity.getEmail());
            log.info("userService : {}", session.getAttribute(SessionConst.LOGIN_USER));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userLoginResponseDto);
        }else {
            log.info("login error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public UserMyInfoResponseDto getUserInfo(Long userId, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Object loginUserId = session.getAttribute(SessionConst.LOGIN_USER);
        UserMyInfoResponseDto userMyInfoResponseDto = new UserMyInfoResponseDto();

        if(loginUserId == userId){
            UserEntity loginedUserEntity = getUserById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));

            userMyInfoResponseDto.setEmail(loginedUserEntity.getEmail());
            userMyInfoResponseDto.setNickname(loginedUserEntity.getNickname());

            return userMyInfoResponseDto;
        }else{
            return null;
        }



    }


    // method for service
    public Optional<UserEntity> getUserByNickname(String nickname) {
        return userRepository.findUserEntityByNickname(nickname);
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findUserEntityByEmail(email);
    }

    public Optional<UserEntity> getUserById(Long userId) {
        return userRepository.findById(userId);
    }



}
