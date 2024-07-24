package sample.codearea.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeTrackableEntity {

	@CreatedDate
	@Column(name = "createdAt", updatable = false, nullable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "modifiedAt", nullable = false)
	protected LocalDateTime updatedAt;
}
