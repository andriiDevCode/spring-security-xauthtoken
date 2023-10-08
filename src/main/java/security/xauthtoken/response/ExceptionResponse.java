package security.xauthtoken.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
    private String path;
    private LocalDateTime time;

    public ExceptionResponse(HttpStatus httpStatus, String message, String path) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
        this.time = LocalDateTime.now();
    }
}
