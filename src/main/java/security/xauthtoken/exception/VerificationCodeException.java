package security.xauthtoken.exception;

public class VerificationCodeException extends RuntimeException {
    public VerificationCodeException() {
        super("Verification code is wrong or expired");
    }
}
