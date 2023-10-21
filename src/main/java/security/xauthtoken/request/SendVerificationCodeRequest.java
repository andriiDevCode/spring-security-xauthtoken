package security.xauthtoken.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationCodeRequest {
    @Email
    @NotBlank
    private String email;
}