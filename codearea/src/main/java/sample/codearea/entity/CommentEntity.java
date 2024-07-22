package sample.codearea.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
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
