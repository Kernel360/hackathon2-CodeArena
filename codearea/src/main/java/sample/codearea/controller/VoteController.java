package sample.codearea.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.codearea.constant.voteStatus;
import sample.codearea.dto.AnswerResponseDto;
import sample.codearea.dto.QuestionResponseDto;
import sample.codearea.entity.VoteEntity;
import sample.codearea.service.QuestionService;
import sample.codearea.service.VoteService;

@RestController
@RequestMapping("/questions/{questionId}")
@RequiredArgsConstructor
@SessionAttributes("user")
public class VoteController {

    private final VoteService voteService;

    @GetMapping("/vote")
    public ResponseEntity<VoteEntity> getVote(@PathVariable Long questionId, HttpServletRequest httpServletRequest) {
        VoteEntity vote = voteService.getVote(questionId, httpServletRequest);
        return ResponseEntity.ok(vote);
    }

    @PutMapping("/vote")
    public ResponseEntity updateVote(@PathVariable Long questionId, @RequestParam voteStatus voteStatus, HttpServletRequest httpServletRequest) {
        voteService.updateVote(questionId, voteStatus, httpServletRequest);
        return ResponseEntity.ok().build();
    }

//    @PatchMapping("/answers/vote")
//    public ResponseEntity<AnswerResponseDto> answerDto() {
//
//    }

}
