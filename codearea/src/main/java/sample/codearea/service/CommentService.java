package sample.codearea.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sample.codearea.dto.CommentRequestDto;
import sample.codearea.dto.CommentResponseDto;
import sample.codearea.entity.AnswerEntity;
import sample.codearea.entity.CommentEntity;
import sample.codearea.entity.UserEntity;
import sample.codearea.repository.AnswerRepository;
import sample.codearea.repository.CommentRepository;
import sample.codearea.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;

    public Optional<CommentEntity> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<CommentResponseDto> findByAnswerId(Long answerId) {
        AnswerEntity answerEntity = answerRepository.findById(answerId).orElse(null);
       return commentRepository.findByAnswer(answerEntity).stream()
            .map(CommentConverter::toDto)
            .collect(Collectors.toList());
    }

    public List<CommentResponseDto> findAll() {

        return commentRepository.findAll().stream()
            .map(CommentConverter::toDto)
            .collect(Collectors.toList());
    }


    public void save(CommentRequestDto commentRequestDto) {

        UserEntity user = userRepository.findById(commentRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Not found Question"));
        AnswerEntity answer = answerRepository.findById(commentRequestDto.getAnswerId()).orElseThrow(()-> new IllegalArgumentException("Not found Question"));


        CommentEntity comment = CommentEntity.builder()
                .user(user)
                .answer(answer)
                .content(commentRequestDto.getContent())
                .build();
        CommentEntity savedComment = commentRepository.save(comment);

        CommentConverter.toDto(savedComment);
    }

    public void update(Long commentId, CommentRequestDto commentRequestDto) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();

        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);
    }


}
