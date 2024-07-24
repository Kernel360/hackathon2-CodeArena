package sample.codearea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.codearea.entity.QuestionEntity;

// 1. 조건 검색 (email, nickname, title, content)
// 2. 정렬 (조회순, 좋아요 순, 싫어요 순)
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {


	Page<QuestionEntity> findAllByUser_EmailContaining(String email, PageRequest pageRequest);

	Page<QuestionEntity> findAllByUser_NicknameContaining(String nickname, PageRequest pageRequest);

	Page<QuestionEntity> findAllByTitleContaining(String title, PageRequest pageRequest);

	Page<QuestionEntity> findAllByContentContaining(String content, PageRequest pageRequest);
}

// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
// https://stackoverflow.com/questions/21456494/spring-jpa-query-with-like
// https://www.baeldung.com/spring-data-jpa-query
// https://www.baeldung.com/spring-data-sorting
/*
	@Query("SELECT q FROM QuestionEntity q "
		+ "WHERE q.user.nickname LIKE CONCAT('%', :nickname, '%') "
		+ "ORDER BY q.views DESC")
	Page<QuestionEntity> findQuestionsByNickname(@Param("nickname") String nickname, Pageable pageable);

	@Query("SELECT q FROM QuestionEntity q "
		+ "WHERE q.user.email LIKE CONCAT('%', :email, '%') "
		+ "ORDER BY q.views DESC")
	Page<QuestionEntity> findQuestionsByEmail(@Param("email") String email, Pageable pageable);
 */