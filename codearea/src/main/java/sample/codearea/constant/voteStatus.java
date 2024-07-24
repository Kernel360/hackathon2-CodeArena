package sample.codearea.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum voteStatus {
    VOTE_STATUS_NOT_VOTED("NOT_VOTED"), VOTE_STATUS_LIKE("LIKE"), VOTE_STATUS_HATE("HATE");
    private String voteStatusType;
}
