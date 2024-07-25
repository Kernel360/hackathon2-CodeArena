package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.constant.SessionConst;
import sample.codearea.constant.voteStatus;
import sample.codearea.entity.VoteEntity;
import sample.codearea.repository.VoteRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    /**
     * 로그인 시 -> 지금 로직 그대로
     * 로그인 안했을 시 -> loginId == null,
     */
    public VoteEntity getVote(Long questionId, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        if(loginId == null) {
            log.info("log : {} ", voteRepository.findByUserQuestionCK_QuestionId(questionId));
            return null;
        }
        UserQuestionCK questionCK = new UserQuestionCK(loginId, questionId);
        Optional<VoteEntity> optionalVote = voteRepository.findById(questionCK);

        if(!optionalVote.isPresent()) {
            return VoteEntity.builder()
                    .userQuestionCK(questionCK)
                    .voteStatus(voteStatus.VOTE_STATUS_NOT_VOTED)
                    .build();
        }
        return optionalVote.get();
    }

    public void updateVote(Long questionId, voteStatus voteStatus, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);

        UserQuestionCK questionCK = new UserQuestionCK(loginId, questionId);
        VoteEntity voteEntity = VoteEntity.builder()
                .userQuestionCK(questionCK)
                .voteStatus(voteStatus)
                .build();

        voteRepository.save(voteEntity);
    }


    // 사용자 투표 상태 확인해서 null 이면 0 넣고, 아니면 사용자의 투표 상태를 가져오기
    /**
     * 1. 내 투표 상태
     *     2. 내가 상태 업데이트
     *     3. 좋아요 개수 카운트
     *             4. 싫어요 개수 카운트
     */
    private Long getLoginId(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Long loginId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return loginId;
    }

}
