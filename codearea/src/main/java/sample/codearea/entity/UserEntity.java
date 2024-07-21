package sample.codearea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.codearea.common.TimeTrackableEntity;

@Entity
@Table(name = "_USER") // TODO: h2에서 user가 예약 키워드라 임시로 설정하였다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends TimeTrackableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Long id;

	@Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	private String email;

	@Column(name = "nickname", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	private String nickname;

	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
	private String password; // TODO: must be encrypted

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
