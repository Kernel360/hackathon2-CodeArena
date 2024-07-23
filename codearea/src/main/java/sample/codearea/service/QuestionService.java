package sample.codearea.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.repository.QuestionRepository;

@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
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
				return questionRepository.findAllByUserEntity_EmailContaining(searchString, pageRequest);
			case ("nickname"):
				return questionRepository.findAllByUserEntity_NicknameContaining(searchString, pageRequest);
			case ("title"):
				return questionRepository.findAllByTitleContaining(searchString, pageRequest);
			case ("content"):
				return questionRepository.findAllByContentContaining(searchString, pageRequest);
			default:
				throw new IllegalArgumentException("Invalid search category: " + searchCategory);
		}
	}
}
