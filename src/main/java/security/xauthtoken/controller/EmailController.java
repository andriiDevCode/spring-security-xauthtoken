package security.xauthtoken.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.xauthtoken.request.SendVerificationCodeRequest;
import security.xauthtoken.request.VerifyCodeRequest;
import security.xauthtoken.service.EmailService;
import security.xauthtoken.userdetails.UserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/resend-code")
    public void resendEmailVerificationCode(@Valid @RequestBody SendVerificationCodeRequest sendVerificationCodeRequest) {
        emailService.sendEmailCode(sendVerificationCodeRequest.getEmail());
    }

    @PutMapping("/verify")
    public void verify(
            @AuthenticationPrincipal UserDetails userPrincipal,
            @Valid @RequestBody VerifyCodeRequest verifyCodeRequest) {
        emailService.verifyUserEmail(
                userPrincipal.getUserId(), verifyCodeRequest.getEmailCode());
    }
}
