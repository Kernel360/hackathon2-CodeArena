package sample.codearea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.codearea.entity.AnswerEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
	public List<AnswerEntity> findByQuestionIdOrderByCreatedAtDesc(Long questionId);
}
