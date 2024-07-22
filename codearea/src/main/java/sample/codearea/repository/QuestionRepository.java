package sample.codearea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.codearea.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	// 1. 조건 검색 (email, nickname, title, content)

	// 2. 정렬 (조회순, 좋아요 순, 싫어요 순)
}
