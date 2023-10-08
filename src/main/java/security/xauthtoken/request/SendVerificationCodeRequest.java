package security.xauthtoken.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationCodeRequest {
    @Email
    @NotNull
    private String email;
}