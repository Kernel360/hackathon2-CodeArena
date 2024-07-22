package sample.codearea.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.codearea.common.TimeTrackableEntity;

import javax.xml.stream.events.Comment;

@Entity
@Table(name = "_USER") // TODO: h2에서 user가 예약 키워드라 임시로 설정하였다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends TimeTrackableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long id;

	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	private String email;

	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	private String nickname;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	private String password; // TODO: must be encrypted

	@OneToMany(mappedBy = "user")
	private List<QuestionEntity> questions = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<AnswerEntity> answers = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<CommentEntity> comments = new ArrayList<>();

	// https://www.baeldung.com/jpa-many-to-many
	// 사용자 질문 투표 Entity는 위 링크 참고 바랍니다.
	@ManyToMany
	@JoinTable(
		name = "USER_QUESTION_SCRAP",
		joinColumns = {@JoinColumn(name="userId")},
		inverseJoinColumns = {@JoinColumn(name="questionId")})
	private List<QuestionEntity> questionScraps = new ArrayList<>();

	@Builder
	private UserEntity(
		Long id,
		String email,
		String nickname,
		String password
	) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}
}
