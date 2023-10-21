package security.xauthtoken.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequest {
    @Size(min = 6, max = 6)
    @NotBlank
    private String emailCode;
}
