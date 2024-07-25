package sample.codearea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.entity.VoteEntity;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, UserQuestionCK> {

    Optional<VoteEntity> findById(UserQuestionCK questionCK);

    List<VoteEntity> findByUserQuestionCK_QuestionId(Long questionId);
}
