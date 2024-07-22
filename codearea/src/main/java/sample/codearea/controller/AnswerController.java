package sample.codearea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.service.AnswerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public List<AnswerEntity> list() {
        List<AnswerEntity> answers = answerService.findAll();
        return answers;
    }

    @PostMapping
    public ResponseEntity<AnswerEntity> save(@PathVariable Long questionId, @Validated AnswerRequestDto answerRequestDto) {
//        answerService.save(answerRequestDto);
        return null;
    }
}
