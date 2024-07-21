package sample.codearea.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import sample.codearea.common.TimeTrackableEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Entity(name = "answer")
public class AnswerEntity extends TimeTrackableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerId", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private Integer likes;

    @ColumnDefault("0")
    private Integer hates;
}
