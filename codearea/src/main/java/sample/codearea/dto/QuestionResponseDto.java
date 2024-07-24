package sample.codearea.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDto {

    private String userName;
    private LocalDateTime createdAt;
    private Integer views;
    private String title;
    private String content;

    /**
     * vote 객체 넣어야 함.
     * {
     *  내 투표 정보 0/1/2 값
     *  좋아요 수
     *  싫어요 수
     * }
     */
}
