package security.xauthtoken.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.xauthtoken.entity.UserEntity;
import security.xauthtoken.exception.BadRequestException;
import security.xauthtoken.repository.UserRepository;
import security.xauthtoken.request.AuthRequest;

import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public void signup(HttpSession httpSession, AuthRequest authRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(authRequest.getEmail());
        if (userEntityOptional.isPresent()) {
            throw new BadRequestException("User with such email already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(authRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userEntity.setVerified(false);
        userRepository.save(userEntity);
        emailService.sendEmailCode(authRequest.getEmail());
        login(httpSession, authRequest.getEmail(), authRequest.getPassword());
    }

    public void login(HttpSession httpSession, String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        httpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);
    }
}
