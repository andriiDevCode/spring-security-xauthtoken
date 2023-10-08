package security.xauthtoken.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ValidationExceptionResponse {
    private HttpStatus httpStatus;
    private String field;
    private String cause;
    private String path;
    private LocalDateTime time;

    public ValidationExceptionResponse(HttpStatus httpStatus, String field, String cause, String path) {
        this.httpStatus = httpStatus;
        this.field = field;
        this.cause = cause;
        this.path = path;
        this.time = LocalDateTime.now();
    }
}