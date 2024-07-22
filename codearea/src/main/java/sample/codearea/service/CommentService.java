package sample.codearea.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.codearea.dto.AnswerRequestDto;
import sample.codearea.dto.CommentRequestDto;
import sample.codearea.dto.CommentTestDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.CommentEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.CommentRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;

    public Optional<CommentEntity> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }


    public CommentEntity save(CommentTestDto commentRequestDto) {

        UserEntity user = userRepository.findById(commentRequestDto.getUserId()).get();
        AnswerEntity answer = answerRepository.findById(commentRequestDto.getAnswerId()).get();


        CommentEntity comment = CommentEntity.builder()
                .user(user)
                .answer(answer)
                .content(commentRequestDto.getContent())
                .build();

        CommentEntity save = commentRepository.save(comment);

        return save;
    }

    public void update(Long commentId, CommentTestDto commentRequestDto) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();

        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);
    }



}
