package sample.codearea.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.codearea.dto.QuestionPreviewResponseDto;
import sample.codearea.entity.QuestionEntity;
import sample.codearea.repository.QuestionRepository;

public class QuestionMapper {
	public static QuestionPreviewResponseDto toQuestionPreviewDto(QuestionEntity questionEntity) {
		return QuestionPreviewResponseDto.builder()
										 .questionId(questionEntity.getId())
										 .title(questionEntity.getTitle())
										 .nickname(questionEntity.getUser().getNickname())
										 .createAt(questionEntity.getCreatedAt())
										 .likes(questionEntity.getLikes())
										 .hates(questionEntity.getHates())
										 .views(questionEntity.getViews())
										 .build();
	}
}
