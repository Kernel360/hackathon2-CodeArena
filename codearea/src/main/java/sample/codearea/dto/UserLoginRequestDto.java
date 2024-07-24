package sample.codearea.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequestDto {
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;


    @NotBlank
    @Size(
            min = 6, max = 20,
            message = "비밀번호는 최소 6자 이상이어야 합니다"
    )
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다"
    )
    private String password;
}
