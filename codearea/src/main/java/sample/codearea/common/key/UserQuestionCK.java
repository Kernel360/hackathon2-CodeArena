package sample.codearea.common.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class UserQuestionCK implements Serializable {
    @Column(name="userId")
    private Long userId;

    @Column(name="questionId")
    private Long questionId;

    public UserQuestionCK(Long userId, Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

}
