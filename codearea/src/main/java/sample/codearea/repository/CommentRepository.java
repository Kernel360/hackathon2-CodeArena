package sample.codearea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.codearea.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
