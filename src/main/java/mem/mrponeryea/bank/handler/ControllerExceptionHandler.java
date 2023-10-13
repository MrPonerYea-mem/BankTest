package mem.mrponeryea.bank.handler;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import mem.mrponeryea.bank.exception.BaseException;
import mem.mrponeryea.bank.exception.model.ErrorMessage;
import mem.mrponeryea.bank.exception.model.ValidationErrorResponse;
import mem.mrponeryea.bank.exception.model.Violation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
        ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
            .map(
                violation -> new Violation(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
                )
            )
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ValidationErrorResponse(violations));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        final List<Violation> violations = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ValidationErrorResponse(violations));
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ErrorMessage> customException(BaseException exception) {
        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new ErrorMessage(exception.getErrorCode() + ": " + exception.getErrorMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> allElseException(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorMessage(exception.getMessage()));
    }
}


