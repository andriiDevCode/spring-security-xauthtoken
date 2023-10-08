package security.xauthtoken.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest, HttpServletRequest httpServletRequest) {
        authService.login(httpServletRequest, authRequest.getEmail(), authRequest.getPassword());
        return new AuthResponse(httpServletRequest.getSession().getId());
    }

    @PostMapping("/signup")
    public AuthResponse signup(@Valid @RequestBody AuthRequest authRequest, HttpServletRequest httpServletRequest) {
        authService.signup(httpServletRequest, authRequest);
        return new AuthResponse(httpServletRequest.getSession().getId());
    }
}
