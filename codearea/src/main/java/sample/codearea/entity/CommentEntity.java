package sample.codearea.entity;

import jakarta.persistence.*;
import lombok.*;
import sample.codearea.common.TimeTrackableEntity;


@Entity
@Table(name = "COMMENT")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends TimeTrackableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answerId", nullable = false)
    private AnswerEntity answer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    @Builder
    protected CommentEntity(
            Long id,
            UserEntity user,
            AnswerEntity answer,
            String content
    ) {
        this.id = id;
        this.user = user;
        this.answer = answer;
        this.content = content;
    }

}
