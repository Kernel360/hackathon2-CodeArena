package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.constant.SessionConst;
import sample.codearea.constant.voteStatus;
import sample.codearea.dto.QuestionRequestDto;
import sample.codearea.dto.QuestionResponseDto;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

	private final UserRepository userRepository;
	private final QuestionRepository questionRepository;
	private final QuestionConverter questionConverter;

	public QuestionResponseDto findQuestion(Long questionId, HttpServletRequest httpServletRequest) {
		QuestionEntity question = questionRepository.findById(questionId)
				.orElseThrow(() -> new IllegalArgumentException("Not Found Question"));

		UserQuestionCK userQuestionCK = new UserQuestionCK((Long) question.getUser().getId(), questionId);
		QuestionResponseDto questionResponseDto;
        questionResponseDto = new QuestionResponseDto().builder()
				.userId(userQuestionCK.getUserId())
				.questionId(userQuestionCK.getQuestionId())
				.userName(question.getUser().getNickname())
				.createdAt(question.getCreatedAt())
				.views(question.getViews())
				.title(question.getTitle())
				.content(question.getContent())
				.voteStatus(voteStatus.VOTE_STATUS_NOT_VOTED)
                .build();


		return questionResponseDto;
	}

	public Page<QuestionEntity> getQuestionsWithPagingAndSorting(
		Integer pageNumber,
		Integer pageSize,
		Sort sortingStrategy
	) {
		return questionRepository.findAll(
			PageRequest.of(
				pageNumber,
				pageSize,
				sortingStrategy
			)
		);
	}

	public Page<QuestionEntity> getQuestionsByContainingStringWithPagingAndSorting(
		String searchCategory,
		String searchString,
		Integer pageNumber,
		Integer pageSize,
		Sort sortingStrategy
	) {
		// TODO : do parameter validation

		PageRequest pageRequest = PageRequest.of(
			pageNumber,
			pageSize,
			sortingStrategy
		);

		switch (searchCategory) {
			case ("email"):
				return questionRepository.findAllByUser_EmailContaining(searchString, pageRequest);
			case ("nickname"):
				return questionRepository.findAllByUser_NicknameContaining(searchString, pageRequest);
			case ("title"):
				return questionRepository.findAllByTitleContaining(searchString, pageRequest);
			case ("content"):
				return questionRepository.findAllByContentContaining(searchString, pageRequest);
			default:
				throw new IllegalArgumentException("Invalid search category: " + searchCategory);
		}
	}

	public QuestionResponseDto createQuestion(QuestionRequestDto questionRequestDto, HttpServletRequest httpServletRequest) {
		Long loginId = getLoginId(httpServletRequest);
		UserEntity user = userRepository.findById(loginId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

		QuestionEntity question = QuestionEntity.builder()
				.user(user)
				.title(questionRequestDto.getTitle())
				.content(questionRequestDto.getContent())
				.build();

		QuestionEntity saved = questionRepository.save(question);

		return questionConverter.toDto(saved, loginId);

	}

	public void updateQuestion(Long questionId, QuestionRequestDto questionRequestDto, HttpServletRequest httpServletRequest) {
		Long loginId = getLoginId(httpServletRequest);
		UserEntity user = userRepository.findById(loginId).orElseThrow(() -> new IllegalArgumentException("Not Found User"));
		QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Not Found Question"));

		if(user == question.getUser()){
			question.setTitle(questionRequestDto.getTitle());
			question.setContent(questionRequestDto.getContent());
			questionRepository.save(question);
		}
		else {
			throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
		}

	}

	public void deleteQuestion(Long questionId, HttpServletRequest httpServletRequest) {
		Long loginId = getLoginId(httpServletRequest);
		UserEntity user = userRepository.findById(loginId).orElseThrow(() -> new IllegalArgumentException("Not Found User"));
		QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Not Found Question"));

		if(user == question.getUser()){
			questionRepository.delete(question);
			return;
		}
		throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
	}

	private static Long getLoginId(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Long loginId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
		log.info("loginId: " + loginId);
		return loginId;
	}
}
