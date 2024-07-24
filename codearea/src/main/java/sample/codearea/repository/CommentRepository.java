package sample.codearea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findByAnswer(AnswerEntity answer);
}
