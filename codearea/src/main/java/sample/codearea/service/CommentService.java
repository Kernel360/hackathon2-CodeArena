package sample.codearea.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sample.codearea.constant.SessionConst;
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
        AnswerEntity answerEntity = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("not found answer"));
        return commentRepository.findByAnswerOrderByCreatedAtDesc(answerEntity).stream()
            .map(CommentConverter::toDto)
            .collect(Collectors.toList());
    }

    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll().stream()
            .map(CommentConverter::toDto)
            .collect(Collectors.toList());
    }


    public void save( Long answerId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        UserEntity user = userRepository.findById(loginId).orElseThrow(() -> new IllegalArgumentException("Not found Question"));
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow(()-> new IllegalArgumentException("Not found Question"));

        CommentEntity comment = CommentEntity.builder()
                .user(user)
                .answer(answer)
                .content(commentRequestDto.getContent())
                .build();

        if(loginId == comment.getUser().getId()){
            CommentEntity savedComment = commentRepository.save(comment);
            CommentConverter.toDto(savedComment); // 아마 response 반환할 때 쓰려고 했는듯
            return;
        }
        throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
    }

    public void update(Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("not found comment"));

        if(loginId == comment.getUser().getId()){
            comment.setContent(commentRequestDto.getContent());
            commentRepository.save(comment);
            return;
        }
        throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
    }

    public void delete(Long commentId, HttpServletRequest httpServletRequest) {
        Long loginId = getLoginId(httpServletRequest);
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("not found comment"));

        if(loginId == comment.getUser().getId()){
            commentRepository.delete(comment);
            return;
        }
        throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
    }

    private static Long getLoginId(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Long loginId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return loginId;
    }

}
