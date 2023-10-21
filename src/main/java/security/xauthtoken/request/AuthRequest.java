package security.xauthtoken.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @Email
    @NotBlank
    private String email;

    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$",
            message = "minimum 8 length, 1 uppercase letter, 1 digit"
    )
    @NotBlank
    private String password;
}
