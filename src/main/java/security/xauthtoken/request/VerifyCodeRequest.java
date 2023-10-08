package security.xauthtoken.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequest {
    @NotNull
    @Size(min = 6, max = 6)
    private String emailCode;
}
