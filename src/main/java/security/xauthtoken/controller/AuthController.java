package security.xauthtoken.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.xauthtoken.request.AuthRequest;
import security.xauthtoken.response.AuthResponse;
import security.xauthtoken.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest, HttpSession httpSession) {
        authService.login(httpSession, authRequest.getEmail(), authRequest.getPassword());
        return new AuthResponse(httpSession.getId());
    }

    @PostMapping("/signup")
    public AuthResponse signup(@Valid @RequestBody AuthRequest authRequest, HttpSession httpSession) {
        authService.signup(httpSession, authRequest);
        return new AuthResponse(httpSession.getId());
    }
}
