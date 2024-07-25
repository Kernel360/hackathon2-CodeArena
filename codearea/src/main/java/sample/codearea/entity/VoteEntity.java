package sample.codearea.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.constant.voteStatus;

@Entity
@Table(name = "USER_QUESTION_VOTE")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VoteEntity {

    @EmbeddedId
    private UserQuestionCK userQuestionCK;

    private voteStatus voteStatus;
}
