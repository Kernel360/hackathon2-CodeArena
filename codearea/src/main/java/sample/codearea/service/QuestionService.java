package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.codearea.constant.SessionConst;
import sample.codearea.dto.QuestionRequestDto;
import sample.codearea.dto.QuestionResponseDto;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.QuestionRepository;
import sample.codearea.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final UserRepository userRepository;
	private final QuestionRepository questionRepository;
	private final QuestionConverter questionConverter;

	public QuestionResponseDto findQuestion(Long questionId) {
		QuestionEntity question = questionRepository.findById(questionId)
				.orElseThrow(() -> new IllegalArgumentException("Not Found Question"));

		return questionConverter.toDto(question);
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

		QuestionResponseDto responseDto = questionConverter.toDto(saved);

		return responseDto;
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
		return loginId;
	}

}
