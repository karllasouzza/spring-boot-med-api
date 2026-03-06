package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ErrorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ErrorHandler400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ErrorsDataValidation::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ValidationErrorHandler(ValidationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record ErrorsDataValidation(String field, String message) {
        public ErrorsDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
