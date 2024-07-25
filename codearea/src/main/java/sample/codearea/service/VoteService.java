package sample.codearea.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.common.key.UserQuestionCK;
import sample.codearea.constant.SessionConst;
import sample.codearea.constant.voteStatus;
import sample.codearea.entity.VoteEntity;
import sample.codearea.repository.VoteRepository;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteEntity getVote(Long questionId, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        return voteRepository.findById(new UserQuestionCK(loginId, questionId)).orElseThrow(() -> new IllegalArgumentException("not found vote"));
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
