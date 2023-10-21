package security.xauthtoken.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import security.xauthtoken.entity.UserEntity;
import security.xauthtoken.entity.VerificationCodeEntity;
import security.xauthtoken.exception.BadRequestException;
import security.xauthtoken.exception.VerificationCodeException;
import security.xauthtoken.repository.UserRepository;
import security.xauthtoken.repository.VerificationCodeRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private static final String GREETINGS = "Hello, here is your verification code: ";
    private static final String SUBJECT = "Verification Code";
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final int MILLISECONDS_IN_24_HOURS = 86400000;

    private final JavaMailSender mailSender;
    private final VerificationCodeRepository verificationCodeRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = MILLISECONDS_IN_24_HOURS)
    @Transactional
    public void deleteExpiredCodes() {
        verificationCodeRepository.deleteByCreatedOnBefore(LocalDateTime.now().minusHours(24));
    }

    public void verifyUserEmail(long userId, String code) {
        Optional<VerificationCodeEntity> emailCodeOptional = verificationCodeRepository.findByCodeAndUserId(code, userId);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (emailCodeOptional.isEmpty() || userEntityOptional.isEmpty()) {
            throw new VerificationCodeException();
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setVerified(true);
        userRepository.save(userEntity);
    }

    @Transactional
    public void sendEmailCode(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new BadRequestException("User with such email was not found");
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String verificationCode = generateCode();
        mailMessage.setTo(email);
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(GREETINGS + verificationCode);
        VerificationCodeEntity verificationCodeEntity = new VerificationCodeEntity();
        verificationCodeEntity.setUser(userOptional.get());
        verificationCodeEntity.setCode(verificationCode);
        verificationCodeRepository.save(verificationCodeEntity);
        mailSender.send(mailMessage);
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        Random secureRandom = new SecureRandom();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append(secureRandom.nextInt(10));
        }
        return code.toString();
    }
}
