package security.xauthtoken.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import security.xauthtoken.exception.BadRequestException;
import security.xauthtoken.exception.VerificationCodeException;
import security.xauthtoken.response.ExceptionResponse;
import security.xauthtoken.response.ValidationExceptionResponse;

@ControllerAdvice
public class ApplicationHandler {
    @ExceptionHandler({BadRequestException.class, VerificationCodeException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> badRequestHandler(RuntimeException ex, ServletWebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequest().getRequestURI()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> validationHandler(
            MethodArgumentNotValidException ex,
            ServletWebRequest request
    ) {
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getFieldError().getField(),
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                request.getRequest().getRequestURI()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
